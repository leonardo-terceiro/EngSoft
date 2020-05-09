package upf.engSoft2.subscriptioncore.exception;

public class UnexpectedErrorException extends RuntimeException {

	private static final long serialVersionUID = 8752492081984579124L;

	public UnexpectedErrorException() {
		super();
	}
	
	public UnexpectedErrorException(String msg) {
		super(msg);
	}
	
}
