package co.kr.metacoding.backendtest.core.error;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String reason;

    public ErrorResponse(String reason) {
        this.reason = reason;
    }
}
