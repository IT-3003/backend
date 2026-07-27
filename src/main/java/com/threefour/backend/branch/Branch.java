package com.threefour.backend.branch;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "branch")

@Entity
public class Branch {

    @Id
    private int branchId;

    private String branchName;
    private String address;
    private String phoneNumber;
    private int managerId;
    private String openingHours;
    private Date createdDate;
    private Date updatedDate;
    private boolean isActive = true;
    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    public Branch() {

    }

    public Branch(int branchId, String branchName, String address, String phoneNumber,
                  int managerId, String openingHours, Date createdDate, Date updatedDate) {
        this.branchId = branchId;
        this.branchName = branchName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.managerId = managerId;
        this.openingHours = openingHours;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public int getBranchId() {
        return branchId;
    }

    public void setBranchId(int branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}