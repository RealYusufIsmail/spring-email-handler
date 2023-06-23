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
package io.github.realyusufismail.springemailhandler;

import io.github.realyusufismail.springemailhandler.codes.StatusCodes;
import io.github.realyusufismail.springemailhandler.env.DotenvConfig;
import io.github.realyusufismail.springemailhandler.env.JsonConfig;
import io.github.realyusufismail.springemailhandler.impl.EmailRestMethodsImpl;
import io.github.realyusufismail.springemailhandler.verfication.JwtUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@Service
@RequestMapping("/api/v1.0.0")
public class Main implements EmailRestMethods {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(Main.class);

	@Autowired
	private JavaMailSender javaMailSender;

	private final EmailRestMethods emailRestMethods = new EmailRestMethodsImpl(javaMailSender);

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
		var token = JwtUtil.generateToken();
		JsonConfig.config.set("JWT_SECRET_KEY", token);
		logger.info("JWT_SECRET_KEY: {}", token);
	}

	@PostMapping("/email/send")
	@Override
	public StatusCodes triggerEmailSend(@RequestHeader("Authorization") String token, @RequestBody EmailBody body) {
		return emailRestMethods.triggerEmailSend(token, body);
	}

	@GetMapping("/email/status")
	@Override
	public StatusCodes getStatus() {
		return emailRestMethods.getStatus();
	}

	@GetMapping("/email")
	public String info() {
		return """
				   To send an email, you need to send a POST request to /api/v1.0.0/email/send with the following body:
				```json
				{
				    "emailTo": "",
				    "emailFrom": "",
				    "subject": "",
				    "body": ""
				}
				```
				You will also need to provide a token in the header of the request. The token is generated using the JWT_SECRET_KEY in the .env file. The token is valid for 1 hour.
				To get the status of the service, you need to send a GET request to /api/v1.0.0/email/status. You will also need to provide a token in the header of the request. The token is generated using the JWT_SECRET_KEY in the .env file. The token is valid for 1 hour.
				""".stripIndent();
	}
}
