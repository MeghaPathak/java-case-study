package exception;

import lombok.Getter;

@Getter
public enum  ErrorCode {
    REPOSITOTY_ERROR("Error while performing repository operations"),
    INTERNAL_ERROR("Internal error"),
    INVALID_REQUEST("Invalid Input");

    private String  message;
    ErrorCode(String  message) {
        this.message = message;
    }
}
