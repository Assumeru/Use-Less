package useless.tokens;

import useless.parser.ConsumedToken;

public class WhiteSpaceToken implements Token {
	@Override
	public ConsumedToken consume(String input, int index) {
		int i = index;
		for(; i < input.length(); i++) {
			if(!Character.isWhitespace(input.charAt(i))) {
				break;
			}
		}
		if(i == index) {
			return null;
		}
		return new ConsumedToken(i - index, null);
	}
}
