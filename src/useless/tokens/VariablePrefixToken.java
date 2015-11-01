package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.variables.Variable;

public abstract class VariablePrefixToken extends PrefixToken {
	public VariablePrefixToken(String token, ParsedVariablePrefixToken result) {
		super(token, result);
	}

	public static abstract class ParsedVariablePrefixToken extends ParsedPrefixToken {
		private static final long serialVersionUID = 127446069327097787L;

		public ParsedVariablePrefixToken(int precedence) {
			super(precedence);
		}

		@Override
		protected ParsedItem getResult(ParsedItem variable) throws ParseException {
			if(variable instanceof Variable) {
				return getResult((Variable) variable);
			}
			return null;
		}

		protected abstract ParsedItem getResult(Variable variable) throws ParseException;
	}
}
