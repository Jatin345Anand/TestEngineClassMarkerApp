package com.edu.lms.utils;

import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEmail {
	public boolean sendMail2(String from,String to,String subject,String msg){
		boolean result=false;
		
		String username=CommonDAO.getCredentials().getId();
		String password=CommonDAO.getCredentials().getPassword();
		
		/*System.out.println("username : "+username );
		System.out.println("password : "+password);
		System.out.println("from : "+from );
		System.out.println("to : "+to );
		System.out.println("subject : "+subject );
		System.out.println("msg : "+msg );*/
		
		Properties props = new Properties();
		
		//props.put("mail.smtp.host", "relay-hosting.secureserver.net"); //SMTP Host
		
	      props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
	      props.put("mail.smtp.port", "587"); //TLS Port
	      props.put("mail.smtp.auth", "true"); //enable authentication
	      props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
	      
		  
		// Get the Session object.
	      Session session = Session.getInstance(props,
	         new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	               return new PasswordAuthentication(username, password);
	            }
	         });

	      try {
	    	  //InternetAddress address =new InternetAddress();
	    	  //address.setAddress(to);
	    	  
	         // Create a default MimeMessage object.
	         Message message = new MimeMessage(session);

	         // Set From: header field of the header.
	         message.setFrom(new InternetAddress(from));

	         // Set To: header field of the header.
	        
	         message.setRecipients(Message.RecipientType.TO,
	            InternetAddress.parse(to));

	         // Set Subject: header field
	         message.setSubject(subject);
	         message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
	         //message.setRecipients(Message.RecipientType.TO, address);

	         // Create the message part
	         BodyPart messageBodyPart = new MimeBodyPart();

	         // Now set the actual message
	         messageBodyPart.setText(msg);

	         // Create a multipar message
	         Multipart multipart = new MimeMultipart();

	         // Set text message part
	         multipart.addBodyPart(messageBodyPart);

	         // Part two is attachment
	         messageBodyPart = new MimeBodyPart();
	    
	         //DataSource source = new FileDataSource(filename);
	         //messageBodyPart.setDataHandler(new DataHandler(source));
	         //messageBodyPart.setFileName(filename);
	         //multipart.addBodyPart(messageBodyPart);

	         // Send the complete message parts
	         message.setContent(multipart);

	         // Send message
	         Transport.send(message);
	         result=true;
	         System.out.println("Sent message successfully....");
	  
	      } catch (MessagingException e) {
	    	  result=false;
	         throw new RuntimeException(e);
	      }
		
		
		return result;
	}
	/*
	public boolean sendMail(String from,String to,String subject,String msg){
		String host="localhost";
		boolean result=false;
		//Get the session object  
	      Properties properties = System.getProperties();  
	      properties.setProperty("mail.smtp.host", host);  
	      Session session = Session.getDefaultInstance(properties);  
	  
	     //compose the message  
	      try{  
	         MimeMessage message = new MimeMessage(session);  
	         message.setFrom(new InternetAddress(from));  
	         message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
	         message.setSubject(subject);  
	         
	         //StringBuilder sb=new StringBuilder();
	         //sb.append("ID : ");
	         
	         //message.setText("Hello, this is example of sending email  ");
	         
	         //message.setText(sb.toString());
	         message.setText(msg);
	  
	         // Send message  
	         Transport.send(message);  
	         result=true;
	         System.out.println("message sent successfully....");  
	  
	      }catch (MessagingException mex) {
	    	  result=false;
	    	  mex.printStackTrace();
	    	  }  
	      return result;
	}
	*/

}
