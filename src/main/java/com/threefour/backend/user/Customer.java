package com.threefour.backend.user;

import jakarta.persistence.Entity;

@Entity
public class Customer extends User {
    private String address;

    public Customer(){
        super();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
