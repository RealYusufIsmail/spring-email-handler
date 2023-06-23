package io.github.realyusufismail.springemailhandler.impl;

import io.github.realyusufismail.springemailhandler.EmailBody;
import io.github.realyusufismail.springemailhandler.EmailRestMethods;
import io.github.realyusufismail.springemailhandler.codes.StatusCodes;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

public class EmailRestMethodsImpl implements EmailRestMethods {
    Map<String, String> emailMap = Map.of(
            "emailTo", "emailTo",
            "emailFrom", "emailFrom",
            "subject", "subject"
    );

    @Override
    public StatusCodes triggerEmailSend(@RequestBody EmailBody body) {
        emailMap.put("emailTo", body.emailTo());
        emailMap.put("emailFrom", body.emailFrom());
        emailMap.put("subject", body.subject());
        return null;
    }

    @Override
    public Boolean send(EmailBody body) {
        return null;
    }

    @Override
    public StatusCodes getStatus() {
        return null;
    }
}
