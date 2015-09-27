package useless.tokens;

import useless.parser.ParseResult;
import useless.parser.Parser;
import useless.parser.TokenParser;

public class SkipNextLine implements TokenParser {
	@Override
	public boolean matches(Parser parser, String token) {
		return token.charAt(0) == ';';
	}

	@Override
	public ParseResult parse(Parser parser, int index, String[] tokens) {
		return new ParseResult(new useless.statements.SkipNextLine());
	}
}
