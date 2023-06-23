/*
 * Copyright 2023 RealYusufIsmail
 *
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *
 * you may not use this file except in compliance with the License.
 *
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */ 
package io.github.realyusufismail.springemailhandler.impl;

import io.github.realyusufismail.springemailhandler.EmailBody;
import io.github.realyusufismail.springemailhandler.EmailRestMethods;
import io.github.realyusufismail.springemailhandler.codes.StatusCodes;
import io.github.realyusufismail.springemailhandler.verfication.JwtUtil;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

public class EmailRestMethodsImpl implements EmailRestMethods {
	private StatusCodes statusCodes;
	private final JavaMailSender javaMailSender;

	public EmailRestMethodsImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Override
	public StatusCodes triggerEmailSend(@RequestHeader("Authorization") String token, @RequestBody EmailBody body) {
		SimpleMailMessage message = new SimpleMailMessage();

		if (body.emailTo() == null || body.emailFrom() == null || body.subject() == null || body.body() == null) {
			return statusCodes = StatusCodes.BAD_REQUEST;
		}

		if (body.emailTo().isBlank() || body.emailFrom().isBlank() || body.subject().isBlank()
				|| body.body().isBlank()) {
			return statusCodes = StatusCodes.BAD_REQUEST;
		}

		if (!JwtUtil.verifyToken(token)) {
			return statusCodes = StatusCodes.UNAUTHORIZED;
		}

		message.setTo(body.emailTo());
		message.setFrom(body.emailFrom());
		message.setSubject(body.subject());
		message.setText(body.body());

		try {
			javaMailSender.send(message);
		} catch (Exception e) {
			return statusCodes = StatusCodes.EMAIL_SEND_FAILED;
		}

		return statusCodes = StatusCodes.SUCCESS;
	}

	@Override
	public StatusCodes getStatus() {
		return statusCodes;
	}
}
