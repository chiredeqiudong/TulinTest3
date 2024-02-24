package com.it.pojo;

/**
 * @author zy293
 * dateValue:当前日期
 * currentPage：当前页码
*/
public class Page {
   private String dateValue;
   private int currentPage;

    public String getDateValue() {
        return dateValue;
    }

    public void setDateValue(String dateValue) {
        this.dateValue = dateValue;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }
}

