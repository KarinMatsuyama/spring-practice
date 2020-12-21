package com.example.springpractice.dao;

import com.example.springpractice.model.Batch;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatchDao extends JpaRepository<Batch, Integer> {
    @Query("select b from Batch b WHERE :envelopeId in elements(b.envelopIds)")
    Batch findByEnvelopeId(@Param("envelopeId") String envelopeId);

    List<BatchSummary> findByCompany(String company);

    List<Batch> findByCompanyAndProcessDateAndCreditDate(String company, String processDate, String creditDate);

    Batch findTopByCompany(String company, Sort sort);
}
