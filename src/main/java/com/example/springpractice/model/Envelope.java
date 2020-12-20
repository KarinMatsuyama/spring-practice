package com.example.springpractice.model;

import java.util.Date;

public class Envelope {
    private String id;
    private String company;
    private String processDate;
    private String creditDate;

    public Envelope() {};

    public String getId() {
        return id;
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

    public void setId(String id) {
        this.id = id;
    }

    public void setCustomer(String company) {
        this.company = company;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public void setCreditDate(String creditDate) {
        this.creditDate = creditDate;
    }

    @Override
    public String toString() {
        return "Envelope{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", processDate=" + processDate +
                ", creditDate=" + creditDate +
                '}';
    }
}
