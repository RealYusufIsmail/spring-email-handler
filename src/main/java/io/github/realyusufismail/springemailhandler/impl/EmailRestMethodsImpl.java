package io.github.realyusufismail.springemailhandler.impl;

import io.github.realyusufismail.springemailhandler.EmailBody;
import io.github.realyusufismail.springemailhandler.EmailRestMethods;
import io.github.realyusufismail.springemailhandler.codes.StatusCodes;
import io.github.realyusufismail.springemailhandler.verfication.JwtUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

public class EmailRestMethodsImpl implements EmailRestMethods {
    private StatusCodes statusCodes;
    private JavaMailSender javaMailSender;


    @Override
    public StatusCodes triggerEmailSend(@RequestHeader("Authorization") String token, @RequestBody EmailBody body) {
        SimpleMailMessage message = new SimpleMailMessage();

        if (body.emailTo() == null || body.emailFrom() == null || body.subject() == null || body.body() == null) {
            return StatusCodes.BAD_REQUEST;
        }

        if (body.emailTo().isBlank() || body.emailFrom().isBlank() || body.subject().isBlank() || body.body().isBlank()) {
            return StatusCodes.BAD_REQUEST;
        }

        if (!JwtUtil.verifyToken(token)) {
            return StatusCodes.UNAUTHORIZED;
        }

        message.setTo(body.emailTo());
        message.setFrom(body.emailFrom());
        message.setSubject(body.subject());
        message.setText(body.body());

        try {
            javaMailSender.send(message);
        } catch (Exception e) {
            return StatusCodes.EMAIL_SEND_FAILED;
        }

        return StatusCodes.SUCCESS;
    }

    @Override
    public StatusCodes getStatus() {
        return null;
    }
}
