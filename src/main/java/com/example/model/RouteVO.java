package com.example.model;

public class RouteVO {
    private String arrival;
    private String department;

    public RouteVO() {
    }

    public RouteVO(String arrival, String department) {
        this.arrival = arrival;
        this.department = department;
    }

    public String getArrival() {
        return arrival;
    }

    public void setArrival(String arrival) {
        this.arrival = arrival;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
