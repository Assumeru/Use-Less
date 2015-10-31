package useless.tokens;

import useless.parser.ConsumedToken;

public interface Token {
	public ConsumedToken consume(String input, int index);
}
