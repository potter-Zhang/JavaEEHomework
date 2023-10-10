package MyContext;

public class MyException extends Exception {
    public enum ErrorCode {INPUT_STREAM_NULL, FILE_NOT_FOUND, DOCUMENT_ERROR,
        CLASS_NOT_FOUND, INSTANTIATE_ERROR, METHOD_NOT_ACCESSIBLE, INVALID_ID,
        PROPERTY_FORMAT_ERROR, SCOPE_ERROR
    }

    private final ErrorCode ec;
    public MyException(ErrorCode ec, String message) {
        super(message);
        this.ec = ec;
    }
    public ErrorCode getErrorCode() {
        return this.ec;
    }

}
