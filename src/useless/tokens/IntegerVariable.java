package useless.tokens;

import java.util.regex.Pattern;

import useless.exceptions.ParseException;
import useless.parser.ParseResult;
import useless.parser.Parser;
import useless.parser.TokenParser;
import useless.statements.LongVariable;

public class IntegerVariable implements TokenParser {
	private Pattern pattern;

	public IntegerVariable() {
		pattern = Pattern.compile("^(-?[0-9]+)$");
	}

	@Override
	public boolean matches(Parser parser, String token) {
		return pattern.matcher(token).matches();
	}

	@Override
	public ParseResult parse(Parser parser, int index, String[] tokens) throws ParseException {
		try {
			return new ParseResult(new useless.statements.IntegerVariable(Integer.parseInt(tokens[index])));
		} catch(NumberFormatException e) {
			try {
				return new ParseResult(new LongVariable(Long.parseLong(tokens[index])));
			} catch(NumberFormatException e1) {
				throw new ParseException("Not a valid number "+tokens[index], index);
			}
		}
	}
}
