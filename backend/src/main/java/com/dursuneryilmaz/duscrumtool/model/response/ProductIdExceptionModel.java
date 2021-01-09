package com.dursuneryilmaz.duscrumtool.model.response;

import java.io.Serializable;

public class ProductIdExceptionModel implements Serializable {
    private static final long serialVersionUID = 1L;

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
