package useless.tokens;

import useless.parser.ParseResult;
import useless.parser.Parser;
import useless.parser.TokenParser;

public class PrintToken implements TokenParser {
	@Override
	public boolean matches(Parser parser, String token) {
		return true;
	}

	@Override
	public ParseResult parse(Parser parser, int index, String[] tokens) {
		parser.getIO().out.println(tokens[index]);
		return new ParseResult(null, 0);
	}
}
