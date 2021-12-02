package com.eazybytes.repo;

public class Employee {
    private int employeeId;
    private String employeeName, location;
    private int zipcode;

    public Employee(int employeeId, String employeeName, String location, int zipcode){
        super();
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.location = location;
        this.zipcode = zipcode;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }
}
