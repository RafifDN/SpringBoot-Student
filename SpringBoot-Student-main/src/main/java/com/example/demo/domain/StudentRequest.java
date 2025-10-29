package com.example.demo.domain;

import java.time.LocalDate;

public class StudentRequest {
    private String fullName;
    private LocalDate dob;
    private String address;

    public StudentRequest() {}

    public StudentRequest(String fullName, LocalDate dob, String address) {
        this.fullName = fullName;
        this.dob = dob;
        this.address = address;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "StudentRequest{" +
                "fullName='" + fullName + '\'' +
                ", dob=" + dob +
                ", address='" + address + '\'' +
                '}';
    }
}
