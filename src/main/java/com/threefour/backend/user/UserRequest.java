package com.threefour.backend.user;

public class UserRequest {
    private String type; // "customer", "staff", or "admin"
    private String firstName;
    private String lastName;
    private String email;
    private String password; // Plain text password from frontend
    private String phoneNumber;
    private Role role;
    private boolean active = true;

    // Subclass specific fields
    private String address;      // For Customer
    private String empID;        // For Staff
    private String position;     // For Staff

    // Getters and Setters
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public Role getRole() { return role; }
    public void setRole(Role role) { this.role = role; }

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmpID() { return empID; }
    public void setEmpID(String empID) { this.empID = empID; }

    public String getPosition() { return position; }
    public void setPosition(String position) { this.position = position; }
}
