package com.dursuneryilmaz.duscrumtool.model.response;

public class ProductIdExceptionModel {
    private String projectCode;

    public ProductIdExceptionModel(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
