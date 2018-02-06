package com.tiufanov.denis.pixformancetask;

import android.support.annotation.NonNull;

class ErrorObject {

    int code;
    String message;

    public ErrorObject(final int code, @NonNull final String message) {
        this.code = code;
        this.message = message;
    }
}
