package com.it.pojo;

import java.util.List;

/**
 * @author zy293
 * PageBean:分页查询
 */
public class PageBean<T> {
    /**
     * tollCount:总记录数
     * rows:当前页码的数据
     * */
    private int tollCount;
    private List<T> rows;

    public int getTollCount() {
        return tollCount;
    }

    public void setTollCount(int tollCount) {
        this.tollCount = tollCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
