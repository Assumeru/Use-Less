package useless.tokens;

import useless.parser.ConsumedToken;
import useless.variables.ParsedStringVariable;

public class StringToken implements Token {
	@Override
	public ConsumedToken consume(String input, int index) {
		if(input.charAt(index) == '"') {
			StringBuilder sb = new StringBuilder();
			for(int i = index + 1; i < input.length(); i++) {
				if(input.charAt(i) == '\\' && i + 1 < input.length()) {
					i++;
				} else if(input.charAt(i) == '"') {
					return new ConsumedToken(i - index + 1, new ParsedStringVariable(sb.toString()));
				}
				sb.append(input.charAt(i));
			}
		}
		return null;
	}
}
