package cs544.aop1.services;

import cs544.aop1.services.interfaces.IEmailSender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;



public class EmailSender implements IEmailSender {
	String outgoingMailServer = "smtp.acme.com";

	public String getOutgoingMailServer() {
		return outgoingMailServer;
	}



	public void sendEmail (String email, String message){
		try {
			Thread.sleep(350);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("EmailSender: sending '"+message+"' to "+email );
	}

	@Override
	public String toString() {
		return String.format("outgoing mail server = %s",this.outgoingMailServer);
	}



}
