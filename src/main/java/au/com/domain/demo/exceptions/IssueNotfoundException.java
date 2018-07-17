package au.com.domain.demo.exceptions;

public class IssueNotfoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IssueNotfoundException(String exception) {
        super(exception);
    }

}