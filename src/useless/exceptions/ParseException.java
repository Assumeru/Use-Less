package useless.exceptions;

public class ParseException extends Exception {
	private static final long serialVersionUID = -7577125556440651983L;
	private int token;

	public ParseException(String message, int token) {
		super(message);
		this.token = token;
	}

	public ParseException(ParseException previous, int token, int line) {
		super("Parse exception on line "+line+" token "+token, previous);
	}

	public int getToken() {
		return token;
	}
}
