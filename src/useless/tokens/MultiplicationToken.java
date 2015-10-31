package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.variables.Variable;

public class MultiplicationToken extends VariableBinaryToken {
	public MultiplicationToken() {
		super("*", new MultiplicationParsedToken());
	}

	public static class MultiplicationParsedToken extends VariableParsedToken {
		private static final long serialVersionUID = -203535892236835772L;

		public MultiplicationParsedToken() {
			super(11);
		}

		@Override
		protected ParsedItem getResult(Variable first, Variable second) throws ParseException {
			return (ParsedItem) first.multiply(second);
		}
	}
}
