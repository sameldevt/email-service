package br.com.samiac.emailservice.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samiac.emailservice.model.entity.email.Email;
import br.com.samiac.emailservice.model.service.EmailService;

@RestController
@RequestMapping("/email-service/send")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping
	public void send(@RequestBody Email email) {
		emailService.send(email);
	}
}
