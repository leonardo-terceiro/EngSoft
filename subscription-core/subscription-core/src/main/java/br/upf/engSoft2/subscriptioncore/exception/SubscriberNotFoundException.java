package br.upf.engSoft2.subscriptioncore.exception;

public class SubscriberNotFoundException extends Exception {

	private static final long serialVersionUID = 8306850609322314303L;

	public SubscriberNotFoundException() {
		super();
	}
	
	public SubscriberNotFoundException(String msg) {
		super(msg);
	}
}
