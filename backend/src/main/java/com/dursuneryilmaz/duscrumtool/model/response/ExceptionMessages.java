package com.dursuneryilmaz.duscrumtool.model.response;

public enum ExceptionMessages {
    MISSING_REQUIRED_FIELD("Missing required field. Please check documentation for required fields"),
    RECORD_ALREADY_EXISTS("Record already exists"),
    INTERNAL_SERVER_ERROR("Internal server error"),
    NO_RECORD_FOUND("Record with provided id is not found"),
    NO_RECORDS_FOUND("There is no record yet"),
    EMAIL_ALREADY_EXIST("The email address already used"),
    AUTHENTICATION_FAILED("Authentication failed"),
    COULD_NOT_UPDATE_RECORD("Could not update record"),
    COULD_NOT_DELETE_RECORD("Could not delete record"),
    EMAIL_ADDRESS_NOT_VERIFIED("Email address could not be verified"),

    NO_STAKE_HOLDER("There is no stake holder in this product."),
    NO_SCRUM_MANAGER("There is no scrum manager in this product."),
    NO_WORKING_DEVELOPER("There is no developer in this product");


    private String exceptionMessage;

    ExceptionMessages(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getExceptionMessage() {
        return exceptionMessage;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

}
