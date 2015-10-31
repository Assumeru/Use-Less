package useless.tokens;

import useless.parser.ConsumedToken;
import useless.parser.ParsedItem;

public abstract class SimpleToken implements Token {
	private String token;
	private ParsedItem result;

	public SimpleToken(String token, ParsedItem result) {
		this.token = token;
		this.result = result;
	}

	@Override
	public ConsumedToken consume(String input, int index) {
		if(input.startsWith(token, index)) {
			return new ConsumedToken(token.length(), result);
		}
		return null;
	}
}
