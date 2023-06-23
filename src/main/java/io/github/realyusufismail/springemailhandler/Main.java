package io.github.realyusufismail.springemailhandler;

import io.github.realyusufismail.springemailhandler.codes.StatusCodes;
import io.github.realyusufismail.springemailhandler.impl.EmailRestMethodsImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1.0.0/email")
public class Main implements EmailRestMethods {
	EmailRestMethods emailRestMethods = new EmailRestMethodsImpl();
	private StatusCodes statusCodes;


	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}


	@PutMapping
	@Override
	public StatusCodes triggerEmailSend(@RequestBody EmailBody body) {
		emailRestMethods.triggerEmailSend(body);
		this.statusCodes = StatusCodes.SUCCESS;
		return statusCodes;
	}

	@Override
	public StatusCodes getStatus() {
		return statusCodes;
	}
}
