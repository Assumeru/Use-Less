package useless.tokens;

import java.util.List;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.variables.ParsedIntegerVariable;
import useless.variables.Variable;

public class SubtractionToken extends SimpleToken {
	public SubtractionToken() {
		super("-", new SubtractionParsedToken());
	}

	public static class SubtractionParsedToken extends ParsedToken {
		private static final long serialVersionUID = 2390401856716724493L;

		public SubtractionParsedToken() {
			super(OPERATION_ADD_SUB);
		}

		@Override
		public boolean parseStatement(List<ParsedItem> tokens, int index) throws ParseException {
			if(index > 0 && index + 1 < tokens.size()) {
				ParsedItem first = tokens.get(index - 1);
				ParsedItem second = tokens.get(index + 1);
				ParsedItem result = getResult(first, second);
				if(result != null) {
					tokens.remove(index + 1);
					tokens.remove(index);
					tokens.set(index - 1, result);
					return true;
				}
			}
			if(index + 1 < tokens.size()) {
				ParsedItem var = tokens.get(index + 1);
				if(var instanceof Variable) {
					tokens.remove(index + 1);
					tokens.set(index, (ParsedItem) ((Variable) var).multiply(new ParsedIntegerVariable(-1)));
					return true;
				}
			}
			throw new ParseException(getClass().getSimpleName() + " expects two tokens", index);
		}

		private ParsedItem getResult(ParsedItem first, ParsedItem second) throws ParseException {
			if(first instanceof Variable && second instanceof Variable) {
				return (ParsedItem) ((Variable) first).subtract((Variable) second);
			}
			return null;
		}
	}
}
