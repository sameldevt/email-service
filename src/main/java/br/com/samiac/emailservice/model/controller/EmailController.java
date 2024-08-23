package br.com.samiac.emailservice.model.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.samiac.emailservice.model.entity.Email;
import br.com.samiac.emailservice.model.service.EmailService;

@RestController
@RequestMapping("/service/send")
public class EmailController {

	@Autowired
	private EmailService emailService;
	
	@PostMapping
	public void send(Email email) {
		emailService.send(email);
	}
}
