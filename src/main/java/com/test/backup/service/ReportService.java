package com.test.backup.service;

import com.test.dao.ReportDAO;
import com.test.model.Report;

import java.util.List;

public class ReportService {
    private ReportDAO reportDAO = new ReportDAO();

    public List<Report> getAllItem() throws Exception{

        return reportDAO.getAllItems();
    }

    public int getTotalRevenue() throws Exception {
        return reportDAO.getTotalRevenue();
    }

    public int getTotalInventory() throws Exception {
        return reportDAO.getTotalInven();
    }

    public int getTotalOrder() throws Exception {
        return reportDAO.getTotalOrder();
    }
}
