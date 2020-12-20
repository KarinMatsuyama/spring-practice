package com.example.springpractice.config;

import com.example.springpractice.model.Customer;
import com.example.springpractice.model.Envelope;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConfig {
    @Autowired
    KafkaProperties kafkaProperties;

    @Bean
    public ConsumerFactory<String, Customer> consumerFactory() {
        return new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new JsonDeserializer<>(Customer.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Customer> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Customer> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());

        return factory;
    }

    @Bean
    public ConsumerFactory<String, Envelope> envelopeConsumerFactory() {
        return  new DefaultKafkaConsumerFactory<>(
                kafkaProperties.buildConsumerProperties(), new StringDeserializer(), new JsonDeserializer<>(Envelope.class)
        );
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Envelope> kafkaListenerEnvelopeContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Envelope> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(envelopeConsumerFactory());

        return factory;
    }
}
