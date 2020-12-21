package com.example.springpractice.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Batch {
    @Id
    @GeneratedValue
    private long id;
    private int batchNumber;
    private String company;
    private String processDate;
    private String creditDate;
    @ElementCollection
    @CollectionTable(uniqueConstraints = @UniqueConstraint(columnNames = {"envelopIds"}))
    private List<String> envelopIds;

    public Batch() {}

    public Batch(int batchNumber, String company, String processDate, String creditDate, String envelopeId) {
        this.batchNumber = batchNumber;
        this.company = company;
        this.processDate = processDate;
        this.creditDate = creditDate;
        this.envelopIds = new ArrayList<String>();
        this.envelopIds.add(envelopeId);
    }

    public long getId() {
        return id;
    }

    public int getBatchNumber() {
        return batchNumber;
    }

    public String getCompany() {
        return company;
    }

    public String getProcessDate() {
        return processDate;
    }

    public String getCreditDate() {
        return creditDate;
    }

    public List<String> getEnvelopIds() {
        return envelopIds;
    }

    public void setBatchNumber(int batchNumber) {
        this.batchNumber = batchNumber;
    }

    public void setEnvelopIds(List<String> envelopIds) {
        this.envelopIds = envelopIds;
    }

    @Override
    public String toString() {
        return "Batch{" +
                "id=" + id +
                ", batchNumber=" + batchNumber +
                ", company='" + company + '\'' +
                ", processDate='" + processDate + '\'' +
                ", creditDate='" + creditDate + '\'' +
                ", envelopIds=" + envelopIds +
                '}';
    }
}
