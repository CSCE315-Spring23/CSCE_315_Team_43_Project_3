package com.team43.project3.smook.service;

import java.io.Serializable;
import java.util.Objects;

public class Excess implements Serializable {
    String name;
    Float excess;
    

    public Excess() {
    }

    public Excess(String name, Float excess) {
        this.name = name;
        this.excess = excess;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getExcess() {
        return this.excess;
    }

    public void setExcess(Float excess) {
        this.excess = excess;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", excess='" + getExcess() + "'" +
            "}";
    }
    
}
