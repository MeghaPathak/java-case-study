package exception;

import lombok.Getter;

@Getter
public class MyException extends Exception {

    private ErrorCode errorCode;
    private String errorMessage;

    public MyException(Throwable cause, ErrorCode errorCode) {
        super(errorCode.getMessage(), cause);
        this.errorCode = errorCode;
    }

}