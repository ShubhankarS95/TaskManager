package pack;

import java.util.Properties;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.Message.RecipientType;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


class SenderAuthenticator extends Authenticator
{
	public PasswordAuthentication getPasswordAuthentication()
	{
		PasswordAuthentication pa = new PasswordAuthentication(
				"************", "*******");
		return pa;
	}
}
public class MailOTPConfirmation {

	public int getOTP(String mailreceiever)
	{
		int resRandom=-1;
		try
		{
			Properties p = new Properties();
			p.put("mail.smtp.host", "smtp.gmail.com");
			p.put("mail.smtp.port", "587");
			p.put("mail.smtp.auth", "true"); // validate email/password of sender
			p.put("mail.smtp.starttls.enable", "true");
			p.put("mail.debug", "true");
			
			SenderAuthenticator ba = new SenderAuthenticator();
			
			// get session object jiske context mai hum mail bhejne wale hain
			Session s = Session.getDefaultInstance(p, ba);
			
			// create object of message to be send in the context of this session
			MimeMessage mm = new MimeMessage(s);
			
			//String rcvrs ="rronit848@gmail.com";
			
			// provide subject
			mm.setSubject("Confirmation of OTP for Task Manager Application");
			
			// specify the types of reciepent
			mm.setRecipients(RecipientType.TO, mailreceiever);
			
			// create body parts
			MimeBodyPart bp1 = new MimeBodyPart();
			
			resRandom = new Random().nextInt((9999 - 100) + 1); 
			//System.out.println(resRandom);
			
			// associate a text with bp1
			bp1.setContent("<p>This is a message sent by Task manager application</p><br>"
					+ "<p>OTP is <b>"+resRandom+"</b></p>", "text/html");
			
			
			// create a multipart to hold body parts
			MimeMultipart mp = new MimeMultipart();
			
			// store all the body parts in multipart
			mp.addBodyPart(bp1);
			
			// store multipart inside message
			mm.setContent(mp);
			
			// send this message to the mail server
			Transport.send(mm);
			
			System.out.println("* main has been sent to the google mail server *");
			
		}
		catch(Exception e)
		{
			System.out.println("error in code "+e);
		}
		return resRandom;
	}
	
	public static void main(String[] args) {
		//new getOTP("i.m.rajatgoyal@gmail.com");
		
	}

}
