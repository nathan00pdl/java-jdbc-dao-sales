package db;

// Raised on a referential integrity violation: a row cannot be deleted while other rows still reference it
public class dbIntegrityException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public dbIntegrityException(String msg){
		super(msg);
	}
}
