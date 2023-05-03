package com.team43.project3.smook.service;

import java.io.Serializable;
import java.util.Objects;

public class Report implements Serializable {
    String name;
    Float amount;
    

    public Report() {
    }

    public Report(String name, Float excess) {
        this.name = name;
        this.amount = excess;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getAmount() {
        return this.amount;
    }

    public void setAmount(Float excess) {
        this.amount = excess;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", excess='" + getAmount() + "'" +
            "}";
    }
    
}
