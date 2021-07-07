package dz.djezzydevs.hrplaning.api;

import dz.djezzydevs.hrplaning.dto.MailRequest;
import dz.djezzydevs.hrplaning.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@RestController
@RequestMapping("/home")


public class HelloWorldController {
/*
	@Autowired
	MailService mailService;

	@Autowired
	private JavaMailSender emailSender;
	@GetMapping("/test")
	public String helloWorld() {
		return "Hello home !!";
	}

*/

}
