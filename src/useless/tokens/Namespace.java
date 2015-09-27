package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParseResult;
import useless.parser.Parser;
import useless.parser.TokenParser;

public class Namespace implements TokenParser {
	@Override
	public boolean matches(Parser parser, String token) {
		return token.charAt(0) == '@';
	}

	@Override
	public ParseResult parse(Parser parser, int index, String[] tokens) throws ParseException {
		return new ParseResult(new useless.statements.Namespace(tokens[index].substring(1)));
	}
}
