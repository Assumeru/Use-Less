package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParseResult;
import useless.parser.Parser;
import useless.parser.TokenParser;

public class Assignment implements TokenParser {
	@Override
	public boolean matches(Parser parser, String token) {
		return token.equals("=");
	}

	@Override
	public ParseResult parse(Parser parser, int index, String[] tokens) throws ParseException {
		if(index == 0) {
			throw new ParseException("Assignment requires a variable to assign to", index);
		} else if(index + 1 >= tokens.length) {
			throw new ParseException("Assignment expects one argument", index);
		}
		return new ParseResult(new useless.statements.Assignment(index));
	}
}
