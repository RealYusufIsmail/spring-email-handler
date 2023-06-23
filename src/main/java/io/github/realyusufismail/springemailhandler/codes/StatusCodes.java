package io.github.realyusufismail.springemailhandler.codes;

public enum StatusCodes {
    SUCCESS(200),
    BAD_REQUEST(400),
    INTERNAL_SERVER_ERROR(500);

    private final int code;

    StatusCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
