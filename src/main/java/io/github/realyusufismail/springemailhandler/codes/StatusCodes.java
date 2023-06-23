package io.github.realyusufismail.springemailhandler.codes;

import lombok.Getter;

@Getter
public enum StatusCodes {
    /**
     * This is returned when the request is successful.
     */
    SUCCESS(200),
    /**
     * This is returned when the request body is invalid.
     */
    BAD_REQUEST(400),
    /**
     * This is returned when an error occurs in the server.
     */
    INTERNAL_SERVER_ERROR(500),
    /**
     * This is returned when the email fails to send.
     */
    EMAIL_SEND_FAILED(412),
    /**
     * This is returned when the token is invalid.
     */
    UNAUTHORIZED(401);

    private final int code;

    StatusCodes(int code) {
        this.code = code;
    }
}
