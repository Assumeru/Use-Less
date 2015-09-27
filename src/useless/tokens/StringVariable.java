package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParseResult;
import useless.parser.Parser;
import useless.parser.TokenParser;

public class StringVariable implements TokenParser {
	@Override
	public boolean matches(Parser parser, String token) {
		return token.charAt(0) == '"';
	}

	@Override
	public ParseResult parse(Parser parser, int index, String[] tokens) throws ParseException {
		StringBuilder builder = new StringBuilder();
		int i = 0;
		int last = tokens[index].length() - 1;
		if(tokens[index].charAt(last) == '"') {
			if(last > 0) {
				builder.append(tokens[index].substring(1, last));
			}
		} else {
			builder.append(tokens[index].substring(1));
			i = index + 1;
			boolean finished = false;
			for(; i < tokens.length; i++) {
				String token = tokens[i];
				last = token.length() - 1;
				builder.append(' ');
				if(token.charAt(last) == '"') {
					builder.append(token.substring(0, last));
					finished = true;
					break;
				} else {
					builder.append(token);
				}
			}
			if(!finished) {
				throw new ParseException("String not terminated", index);
			}
		}
		return new ParseResult(new useless.statements.StringVariable(builder.toString()), i);
	}
}
