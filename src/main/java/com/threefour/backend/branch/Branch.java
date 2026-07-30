package com.threefour.backend.branch;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Table(name = "branch")
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Branch {

    @Id
    @Positive(message = "Branch ID must be greater than 0")
    private int branchId;

    @NotBlank(message = "Branch name is required")
    @Size(min = 3, max = 100)
    private String branchName;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Phone number is required")
    @Pattern(
            regexp = "^0\\d{9}$",
            message = "Phone number must contain exactly 10 digits"
    )
    private String phoneNumber;

    @Positive(message = "Manager ID must be greater than 0")
    private int managerId;

    @NotBlank(message = "Opening hours are required")
    private String openingHours;

    private Date createdDate;
    private Date updatedDate;

    private boolean isActive = true;
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
        this.isActive = true;
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


    public boolean isActive() {
        return isActive;
    }


    public void setActive(boolean active) {
        isActive = active;
    }
}