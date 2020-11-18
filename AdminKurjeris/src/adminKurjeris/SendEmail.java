package adminKurjeris;

import java.io.FileInputStream;

import java.io.IOException;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class SendEmail {
	
	
	

	public void sendEmail(String recepient, String name, String lastName) throws MessagingException, IOException {
		
	
		
		System.out.println("Ruosiama issiusti zinute");

		FileInputStream in = new FileInputStream("propertiesMail");
		Properties properties = new Properties();
		properties.load(in);

		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");

		String myAccountEmail = properties.getProperty("db.username");
		String password = properties.getProperty("db.password");
		;

		Session session = Session.getInstance(properties, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(myAccountEmail, password);
			}
		});
		Message message = prepareMessage(session, myAccountEmail, recepient, name, lastName);

		Transport.send(message);
		System.out.println("Zinute issiusta");
	}

	private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String name,
			String lastName) {
		
		final String password2= Kurjeris.password2;
		

		Message message = new MimeMessage(session);
		try {
			message.setFrom(new InternetAddress(myAccountEmail));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
			message.setSubject("Vartotojo vardas ir slaptazodis");
			message.setText("Laba diena, \n Jusu vartotojo vardas -" + name.substring(0, 2).concat(lastName)
					+ "\n Jusu slaptazodis " + password2 + "\n Geros dienos, \n CourierLt");
			return message;
		} catch (MessagingException e) {

			e.printStackTrace();
		}
		return null;

	}

}
