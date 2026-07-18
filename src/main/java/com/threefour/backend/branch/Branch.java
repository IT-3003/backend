package com.threefour.backend.branch;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Branch {
    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    @Id
    private int branchId;

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    private String branchName;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private String address;
    private String phoneNumber;
    private int managerId;

    public String getOpeningHours() {
        return openingHours;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getManagerId() {
        return managerId;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }


    private String openingHours;
    private Date createdDate;
    private Date updatedDate;


    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Branch(int branchId, String branchName, String address, String phoneNumber, int managerId, String openingHours, Date createdDate, Date updatedDate) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.managerId = managerId;
        this.openingHours = openingHours;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }


    public Branch() {


    }
}