package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.variables.Variable;

public abstract class VariableBinaryToken extends BinaryToken {
	public VariableBinaryToken(String token, VariableParsedToken result) {
		super(token, result);
	}

	public static abstract class VariableParsedToken extends BinaryParsedToken {
		private static final long serialVersionUID = 231436362847223842L;

		public VariableParsedToken(int precedence) {
			super(precedence);
		}

		@Override
		protected ParsedItem getResult(ParsedItem first, ParsedItem second) throws ParseException {
			if(first instanceof Variable && second instanceof Variable) {
				return getResult((Variable)first, (Variable)second);
			}
			throw new ParseException(getClass().getSimpleName() + " expects two variables", 0);
		}

		protected abstract ParsedItem getResult(Variable first, Variable second) throws ParseException;
	}
}
