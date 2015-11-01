package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.tokens.NameToken.Name;

public abstract class NamePrefixToken extends PrefixToken {
	public NamePrefixToken(String token, ParsedNamePrefixToken result) {
		super(token, result);
	}

	public static abstract class ParsedNamePrefixToken extends ParsedPrefixToken {
		private static final long serialVersionUID = -7388904271072581201L;

		public ParsedNamePrefixToken(int precedence) {
			super(precedence);
		}

		@Override
		protected ParsedItem getResult(ParsedItem variable) throws ParseException {
			if(variable instanceof Name) {
				return getResult((Name) variable);
			}
			throw new ParseException(getClass().getSimpleName()+" expects a name", -1);
		}

		protected abstract ParsedItem getResult(Name variable) throws ParseException;
	}
}
