package com.minkatec.Diary.exceptions;

public class UserNotConfirmedException extends RuntimeException {

    private static final String DESCRIPTION = "User Not Confirmed Exception";

    public UserNotConfirmedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
