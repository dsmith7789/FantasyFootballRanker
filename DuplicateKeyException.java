package application;

/**
 * Checked exception thrown if the key being inserted already exists in the structure.
 * You can use this class as written. 
 */
@SuppressWarnings("serial") 
class DuplicateKeyException extends Exception {
	public DuplicateKeyException() {
		super("Duplicate Key is not permitted");
	}   

	public DuplicateKeyException(String message) {
		super(message);
	}   
}
