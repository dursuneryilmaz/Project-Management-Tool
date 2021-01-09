package com.dursuneryilmaz.duscrumtool.model.response;

import java.io.Serializable;

public class OperationModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private String operationName;
    private String operationStatus;

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public String getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(String operationStatus) {
        this.operationStatus = operationStatus;
    }
}
