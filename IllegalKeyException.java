package application;

/**
 * Checked exception thrown when a user attempts to insert or get a null key.
 * You may use this class as written.
 */
@SuppressWarnings("serial") 
class IllegalKeyException extends Exception {
	public IllegalKeyException() {
		super("An Illegal Key has been used.");
	}   

	public IllegalKeyException(String message) {
		super(message);
	}   
}
