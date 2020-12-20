package com.example.springpractice.consumer;

import com.example.springpractice.BatchConfig;
import com.example.springpractice.dao.BatchDao;
import com.example.springpractice.dao.CustomerDao;
import com.example.springpractice.model.Batch;
import com.example.springpractice.model.Customer;
import com.example.springpractice.model.Envelope;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class Consumer {
    @Autowired
    CustomerDao customerDao;

    @Autowired
    BatchDao batchDao;

    @KafkaListener(topics = "customerProfile", groupId = "group_id")
    public void consumeMessage(Customer customer) {
        System.out.println(customer);
        customerDao.save(customer);
    }

    @KafkaListener(topics = "envelope", groupId = "group_id", containerFactory = "kafkaListenerEnvelopeContainerFactory")
    public void consumeEnvelope(Envelope envelope) {
        System.out.println(envelope);

        BatchConfig batchConfig = new BatchConfig();
        List<Batch> existingBatches = batchDao.findByCompanyAndProcessDateAndCreditDate(envelope.getCompany(), envelope.getProcessDate(), envelope.getCreditDate());
        Batch lastExistingBatch = existingBatches.size() > 0 ? existingBatches.get(existingBatches.size() - 1) : null;

        if (lastExistingBatch != null && lastExistingBatch.getEnvelopIds().size() < batchConfig.getSize()) {
            List<String> envelopeIds = lastExistingBatch.getEnvelopIds();
            envelopeIds.add(envelope.getId());
            lastExistingBatch.setEnvelopIds(envelopeIds);
        } else {
            Batch lastBatchByCompany = batchDao.findTopByCompany(envelope.getCompany(), Sort.by(Sort.Direction.DESC, "batchNumber"));

            int batchNumber = lastBatchByCompany != null ? lastBatchByCompany.getBatchNumber() + 1 : batchConfig.getStart();

            if (batchNumber <= batchConfig.getEnd()) {
                Batch batch = new Batch(batchNumber, envelope.getCompany(), envelope.getProcessDate(), envelope.getCreditDate(), envelope.getId());
                batchDao.save(batch);
            }
        }
    }
}
