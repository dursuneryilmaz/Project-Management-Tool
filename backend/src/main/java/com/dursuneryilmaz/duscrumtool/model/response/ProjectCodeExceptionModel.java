package com.dursuneryilmaz.duscrumtool.model.response;

import java.io.Serializable;

public class ProjectCodeExceptionModel  implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 7181587494265238526L;
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
