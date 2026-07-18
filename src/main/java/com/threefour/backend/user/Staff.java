package com.threefour.backend.user;

import jakarta.persistence.Entity;

@Entity
public class Staff extends User {
    private String empID;
    private String position;

    public Staff(){
        super();
    }

    public String getEmpID() {
        return empID;
    }

    public void setEmpID(String empID) {
        this.empID = empID;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
