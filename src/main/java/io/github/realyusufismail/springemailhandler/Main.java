package io.github.realyusufismail.springemailhandler;

import io.github.realyusufismail.springemailhandler.codes.StatusCodes;
import io.github.realyusufismail.springemailhandler.impl.EmailRestMethodsImpl;
import io.github.realyusufismail.springemailhandler.verfication.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1.0.0/email")
public class Main implements EmailRestMethods {
	private final EmailRestMethods emailRestMethods = new EmailRestMethodsImpl();


	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}


	@PutMapping
	@Override
	public StatusCodes triggerEmailSend(@RequestHeader("Authorization") String token,  @RequestBody EmailBody body) {
		return emailRestMethods.triggerEmailSend(token, body);
	}

	@GetMapping("/status")
	@Override
	public StatusCodes getStatus() {
		return emailRestMethods.getStatus();
	}
}
