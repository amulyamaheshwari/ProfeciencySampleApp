package com.amulya.test.pojo;

import java.util.List;

public class RootDataModel {
    private String title;
    private List<DataModel> rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<DataModel> getRows() {
        return rows;
    }

    public void setRows(List<DataModel> rows) {
        this.rows = rows;
    }
}
