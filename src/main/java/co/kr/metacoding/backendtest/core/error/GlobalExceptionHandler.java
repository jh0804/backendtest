package co.kr.metacoding.backendtest.core.error;

import co.kr.metacoding.backendtest.core.error.ex.ExceptionApi400;
import co.kr.metacoding.backendtest.core.error.ex.ExceptionApi404;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ExceptionApi400.class)
    public ResponseEntity<ErrorResponse> exApi400(ExceptionApi400 e) {
        log.warn(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(ExceptionApi404.class)
    public ResponseEntity<ErrorResponse> exApi404(ExceptionApi404 e) {
        log.warn(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse(e.getMessage()));
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ErrorResponse> noMatchApi404(NoHandlerFoundException  e) {
        log.warn(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse (e.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exUnKnown(Exception e) {
        log.error(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse("관리자에게 문의하세요"));
    }
}