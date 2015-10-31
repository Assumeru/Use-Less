package useless.tokens;

import java.util.List;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;

public abstract class BinaryToken extends SimpleToken {
	public BinaryToken(String token, BinaryParsedToken result) {
		super(token, result);
	}

	public abstract static class BinaryParsedToken extends ParsedToken {
		private static final long serialVersionUID = -8973625368246753744L;

		public BinaryParsedToken(int precedence) {
			super(precedence);
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
			throw new ParseException(getClass().getSimpleName() + " expects two tokens", index);
		}

		protected abstract ParsedItem getResult(ParsedItem first, ParsedItem second) throws ParseException;
	}
}
