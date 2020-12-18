package com.dursuneryilmaz.dupmtool.model.response;

public class ProjectCodeExceptionModel {
    private String projectCode;

    public ProjectCodeExceptionModel(String projectCode) {
        this.projectCode = projectCode;
    }

    public String getProjectCode() {
        return projectCode;
    }

    public void setProjectCode(String projectCode) {
        this.projectCode = projectCode;
    }
}
