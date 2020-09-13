package com.pneto.chatapplication.error.exception;

import com.pneto.chatapplication.error.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class BaseException extends RuntimeException {
    private static final long serialVersionUID = -1299322315819925716L;

    private final ErrorCode errorCode;
}
