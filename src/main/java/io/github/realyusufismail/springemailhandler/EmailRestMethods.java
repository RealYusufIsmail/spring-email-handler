package io.github.realyusufismail.springemailhandler;

import io.github.realyusufismail.springemailhandler.codes.StatusCodes;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public interface EmailRestMethods {
    StatusCodes triggerEmailSend(@RequestHeader("Authorization") String token, @RequestBody EmailBody body);

    StatusCodes getStatus();
}
