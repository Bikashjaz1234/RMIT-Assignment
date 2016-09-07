package lms.model.exception;

//the Exception to show the errorMessage
public class FullException extends Exception {
	public FullException(String errorMessage){
		super(errorMessage);
	}
}
