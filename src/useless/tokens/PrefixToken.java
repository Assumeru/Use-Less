package useless.tokens;

import java.util.List;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;

public abstract class PrefixToken extends SimpleToken {
	public PrefixToken(String token, ParsedPrefixToken result) {
		super(token, result);
	}

	public static abstract class ParsedPrefixToken extends ParsedToken {
		private static final long serialVersionUID = 5260854917381500696L;

		public ParsedPrefixToken(int precedence) {
			super(precedence);
		}

		@Override
		public boolean parseStatement(List<ParsedItem> tokens, int index) throws ParseException {
			if(index + 1 < tokens.size()) {
				ParsedItem variable = tokens.get(index + 1);
				ParsedItem result = getResult(variable);
				if(result != null) {
					tokens.remove(index + 1);
					tokens.set(index, result);
					return true;
				}
			}
			throw new ParseException(getClass().getSimpleName() + " expects a variable", index);
		}

		protected abstract ParsedItem getResult(ParsedItem variable) throws ParseException;
	}
}
