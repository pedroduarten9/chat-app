package com.pneto.chatapplication.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.pneto.chatapplication.error.ErrorCode;
import com.pneto.chatapplication.error.ResponseError;
import com.pneto.chatapplication.error.exception.BaseException;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.support.MethodArgumentNotValidException;
import org.springframework.messaging.handler.annotation.support.MethodArgumentTypeMismatchException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ChatControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ResponseError> handleMethodArgumentNotValidException() {
        return ResponseEntity.status(ErrorCode.GENERIC_BAD_REQUEST.getHttpStatus())
                .body(new ResponseError(ErrorCode.GENERIC_BAD_REQUEST));
    }

    @ExceptionHandler(InvalidFormatException.class)
    ResponseEntity<ResponseError> handleInvalidFormatException() {
        return ResponseEntity.status(ErrorCode.GENERIC_BAD_REQUEST.getHttpStatus())
                .body(new ResponseError(ErrorCode.GENERIC_BAD_REQUEST));
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    ResponseEntity<ResponseError> handleMethodArgumentTypeMismatchException() {
        return ResponseEntity.status(ErrorCode.GENERIC_BAD_REQUEST.getHttpStatus())
                .body(new ResponseError(ErrorCode.GENERIC_BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ResponseError> handleException() {
        return ResponseEntity.status(ErrorCode.GENERIC_INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(new ResponseError(ErrorCode.GENERIC_INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(BaseException.class)
    ResponseEntity<ResponseError> handleBaseException(BaseException ex) {
        return ResponseEntity.status(ex.getErrorCode().getHttpStatus())
                .body(new ResponseError(ex.getErrorCode()));
    }
}
