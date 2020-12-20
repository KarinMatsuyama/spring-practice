package com.example.springpractice.controller;

import com.example.springpractice.dao.BatchDao;
import com.example.springpractice.dao.BatchSummary;
import com.example.springpractice.model.Batch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BatchController {

    @Autowired
    BatchDao dao;

    @RequestMapping("/batch/{envelopeId}")
    @ResponseBody
    public int getBatchNumberByEnvelopeId(@PathVariable("envelopeId") String envelopeId) {
        Batch batch = dao.findByEnvelopeId(envelopeId);
        return batch.getBatchNumber();
    }

    @RequestMapping("/batches/{company}")
    @ResponseBody
    public List<BatchSummary> getBatchesByCompany(@PathVariable("company") String company) {
        return dao.findByCompany(company);
    }

    @RequestMapping("/batches/{company}/{processDate}/{creditDate}")
    @ResponseBody
    public List<Integer> getBatchNumberByCompanyProcessDateCreditDate(@PathVariable("company") String company, @PathVariable("processDate") String processDate, @PathVariable("creditDate") String creditDate) {
        List<Batch> batches = dao.findByCompanyAndProcessDateAndCreditDate(company, processDate, creditDate);
        List<Integer> batchNumbers = new ArrayList<Integer>();
        for (Batch batch : batches) {
            batchNumbers.add(batch.getBatchNumber());
        }
        return batchNumbers;
    }
}
