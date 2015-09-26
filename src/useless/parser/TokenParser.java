package useless.parser;

import useless.exceptions.ParseException;

public interface TokenParser {
	public boolean matches(Parser parser, String token);

	public ParseResult parse(Parser parser, int index, String[] tokens) throws ParseException;
}
