package fr.gtm.cinema.util;

import java.io.Serializable;
//pojo utilisé come mock pour un client
public class MailReceptor implements Serializable{
	private String mail;

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}
	
}
