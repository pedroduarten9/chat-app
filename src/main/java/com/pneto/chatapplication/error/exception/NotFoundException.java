package com.pneto.chatapplication.error.exception;

import com.pneto.chatapplication.error.ErrorCode;

public class NotFoundException extends BaseException {
    private static final long serialVersionUID = 5895833648034514356L;

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
