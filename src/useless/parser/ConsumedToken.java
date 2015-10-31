package useless.parser;


public class ConsumedToken {
	private int length;
	private ParsedItem token;

	public ConsumedToken() {
		this(0, null);
	}

	public ConsumedToken(int length, ParsedItem token) {
		this.length = length;
		this.token = token;
	}

	public int getLength() {
		return length;
	}

	public ParsedItem getToken() {
		return token;
	}
}
