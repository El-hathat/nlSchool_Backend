package com.SchoolWebSite.Services;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;


@Service
public class EmailService {

	 private final JavaMailSender mailSender;

	    public EmailService(JavaMailSender mailSender) {
	        this.mailSender = mailSender;
	    }

	    public void sendNewPasswordEmail(String toEmail, String newPassword) throws MessagingException {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

	        String htmlMsg = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\">" +
	                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
	                "<style>/* Style ici */</style></head><body>" +
	                "<div class=\"email-container\">" +
	                "<div class=\"email-header\"><h1>NL School</h1></div>" +
	                "<div class=\"email-body\"><h2>Votre nouveau mot de passe</h2>" +
	                "<p>Bonjour,</p>" +
	                "<p>Vous avez demandé un nouveau mot de passe pour votre compte. Utilisez le mot de passe suivant pour vous connecter :</p>" +
	                "<p class=\"password\">" + newPassword + "</p>" +
	                "<p>Pour des raisons de sécurité, il est recommandé de changer ce mot de passe après votre connexion.</p>" +
	                "<p>Si vous n'avez pas fait cette demande, veuillez ignorer cet email ou contacter notre support.</p></div>" +
	                "<div class=\"email-footer\"><p>Merci de nous faire confiance.</p>" +
	                "<p><a href=\"https://nl-digitalagency.com/\">NL School</a> | &copy; 2024 Tous droits réservés.</p></div>" +
	                "</div></body></html>";

	        helper.setText(htmlMsg, true);  // true pour indiquer que le contenu est en HTML
	        helper.setTo(toEmail);
	        helper.setSubject("Votre nouveau mot de passe");
	        helper.setFrom("your-email@example.com");

	        mailSender.send(mimeMessage);
	    }
	    
	    
	    
	    
	    public void sendInscriptionEmail(String toEmail, String name) throws MessagingException {
	        MimeMessage mimeMessage = mailSender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

	        String htmlMsg = "<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\">" +
	                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
	                "<style>/* Style ici */</style></head><body>" +
	                "<div class=\"email-container\">" +
	                "<div class=\"email-header\"><h1>NL School</h1></div>" +
	                
	                "<p>Bonjour,"+name+"</p>" +
	                "<p>Votre compte a ete creer avec succes</p>" +
	                "<p>Nous répondrons dans les plus brefs délais</p>" +
	                "<p>Si vous n'avez pas fait cette demande, veuillez ignorer cet email ou contacter notre support.</p></div>" +
	                "<div class=\"email-footer\"><p>Merci de nous faire confiance.</p>" +
	                "<p><a href=\"https://nl-digitalagency.com/\">NL School</a> | &copy; 2024 Tous droits réservés.</p></div>" +
	                "</div></body></html>";

	        helper.setText(htmlMsg, true);  // true pour indiquer que le contenu est en HTML
	        helper.setTo(toEmail);
	        helper.setSubject("Inscription avec succes");
	        helper.setFrom("nlschool@gmail.com");

	        mailSender.send(mimeMessage);
	    }
}

