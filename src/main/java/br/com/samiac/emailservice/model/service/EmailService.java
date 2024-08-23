package br.com.samiac.emailservice.model.service;

import org.springframework.stereotype.Service;

import br.com.samiac.emailservice.model.entity.Email;
import br.com.samiac.emailservice.sender.Sender;

@Service
public class EmailService {

	public void send(Email email) {
		Sender.sendEmail(email);
	}
}
