package useless.tokens;

import useless.parser.ConsumedToken;
import useless.parser.ParsedItem;
import useless.variables.ParsedLongVariable;
import useless.variables.ParsedIntegerVariable;
import useless.variables.ParsedShortVariable;
import useless.variables.ParsedVariable;

public class IntegerToken implements Token {
	@Override
	public ConsumedToken consume(String input, int index) {
		if(!isNumber(input.charAt(index))) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = index; i < input.length() && isNumber(input.charAt(i)); i++) {
			sb.append(input.charAt(i));
		}
		if(sb.length() > 0) {
			ParsedItem value = getValue(sb.toString());
			if(value != null) {
				return new ConsumedToken(sb.length(), value);
			}
		}
		return null;
	}

	private ParsedItem getValue(String string) {
		try {
			long value = Long.parseLong(string, 10);
			return createVariable(value);
		} catch(NumberFormatException e) {
			return null;
		}
	}

	private boolean isNumber(char c) {
		return c >= '0' && c <= '9';
	}

	public static ParsedVariable createVariable(long value) {
		if(value <= Short.MAX_VALUE && value >= Short.MIN_VALUE) {
			return new ParsedShortVariable((short) value);
		} else if(value <= Integer.MAX_VALUE && value >= Integer.MIN_VALUE) {
			return new ParsedIntegerVariable((int) value);
		}
		return new ParsedLongVariable(value);
	}
}
