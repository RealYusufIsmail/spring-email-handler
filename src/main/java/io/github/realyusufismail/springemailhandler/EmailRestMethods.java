package io.github.realyusufismail.springemailhandler;

import io.github.realyusufismail.springemailhandler.codes.StatusCodes;
import org.springframework.web.bind.annotation.RequestBody;

public interface EmailRestMethods {
    StatusCodes triggerEmailSend(@RequestBody EmailBody body);

    Boolean send(EmailBody body);

    StatusCodes getStatus();
}
