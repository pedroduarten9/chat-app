package com.pneto.chatapplication.error;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    GENERIC_BAD_REQUEST(HttpStatus.BAD_REQUEST, 1, "invalid request"),

    NOT_FOUND_CHAT(HttpStatus.NOT_FOUND, 1, "Chat not found"),

    GENERIC_INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, 1, "generic internal server error");

    private HttpStatus httpStatus;
    private int internalCode;
    private String description;
}