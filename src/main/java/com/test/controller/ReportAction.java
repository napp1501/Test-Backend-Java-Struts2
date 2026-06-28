package com.test.controller;

import com.test.model.Report;
import com.test.service.ReportService;

import java.util.List;

public class ReportAction {
    private List<Report> reports;
    private int totalRevenue;
    private int totalInventory;
    private int totalOrder;

    private ReportService service = new ReportService();

    public String execute() throws Exception {
        reports = service.getAllItem();
        totalRevenue = service.getTotalRevenue();
        totalInventory = service.getTotalInventory();
        totalOrder = service.getTotalOrder();
        return "success";
    }

    public int getTotalOrder() {
        return totalOrder;
    }

    public void setTotalOrder(int totalOrder) {
        this.totalOrder = totalOrder;
    }

    public int getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(int totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public int getTotalInventory() {
        return totalInventory;
    }

    public void setTotalInventory(int totalInventory) {
        this.totalInventory = totalInventory;
    }

    public List<Report> getReports() {
        return reports;
    }

    public void setReports(List<Report> reports) {
        this.reports = reports;
    }

    public ReportService getService() {
        return service;
    }

    public void setService(ReportService service) {
        this.service = service;
    }
}
