package com.yeaile.common.result;

import java.util.List;

public class PageResult<T> {
    private long total;
    private List<T> rows;
    private ResultPage resultPage;


    public PageResult() {
    }

    public PageResult(long total, List<T> rows) {
        this.total = total;
        this.rows = rows;
    }


    public PageResult(long total, List<T> rows, ResultPage resultPage) {
        this.total = total;
        this.rows = rows;
        this.resultPage = resultPage;
    }

    public ResultPage getResultPage() {
        return resultPage;
    }

    public void setResultPage(ResultPage resultPage) {
        this.resultPage = resultPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}
