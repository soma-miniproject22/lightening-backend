package com.soma.lightening.exception;

import com.soma.lightening.common.controller.dto.CommonResponse;
import com.soma.lightening.common.controller.dto.ErrorResponse;
import com.soma.lightening.exception.error.DuplicateMemberException;
import com.soma.lightening.exception.error.InvalidRefreshTokenException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Bean Validation에 실패했을 때, 에러메시지를 내보내기 위한 Exception Handler
     */
    @ExceptionHandler(BindException.class)
    protected ResponseEntity<CommonResponse> handleParamViolationException(BindException ex) {
        // 파라미터 validation에 걸렸을 경우
        ErrorCode errorCode = ErrorCode.REQUEST_PARAMETER_BIND_FAILED;

        ErrorResponse error = ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .build();

        CommonResponse response = CommonResponse.builder()
                .error(error)
                .build();
        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    @ExceptionHandler(DuplicateMemberException.class)
    protected ResponseEntity<CommonResponse> handleDuplicateMemberException(DuplicateMemberException ex) {
        ErrorCode errorCode = ErrorCode.DUPLICATE_MEMBER_EXCEPTION;

        ErrorResponse error = ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .build();

        CommonResponse response = CommonResponse.builder()
                .error(error)
                .build();
        return new ResponseEntity<>(response, errorCode.getStatus());
    }

    @ExceptionHandler(InvalidRefreshTokenException.class)
    protected ResponseEntity<CommonResponse> handleInvalidRefreshTokenException(InvalidRefreshTokenException ex) {
        ErrorCode errorCode = ErrorCode.INVALID_REFRESH_TOKEN;

        ErrorResponse error = ErrorResponse.builder()
                .status(errorCode.getStatus().value())
                .message(errorCode.getMessage())
                .code(errorCode.getCode())
                .build();

        CommonResponse response = CommonResponse.builder()
                .error(error)
                .build();
        return new ResponseEntity<>(response, errorCode.getStatus());
    }
}
