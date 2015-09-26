package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParseResult;
import useless.parser.Parser;
import useless.parser.TokenParser;

public class PrintMemory implements TokenParser {
	@Override
	public boolean matches(Parser parser, String token) {
		return token.equals("printMemory");
	}

	@Override
	public ParseResult parse(Parser parser, int index, String[] tokens) throws ParseException {
		return new ParseResult(new useless.statements.PrintMemory(), 0);
	}
}
