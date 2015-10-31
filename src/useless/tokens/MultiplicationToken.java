package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.variables.Variable;

public class MultiplicationToken extends VariableBinaryToken {
	public MultiplicationToken() {
		super("*", new MultiplicationParsedToken());
	}

	public static class MultiplicationParsedToken extends ParsedVariableToken {
		private static final long serialVersionUID = -5419673784964755370L;

		public MultiplicationParsedToken() {
			super(OPERATION_MULT_DIV);
		}

		@Override
		protected ParsedItem getResult(Variable first, Variable second) throws ParseException {
			return (ParsedItem) first.multiply(second);
		}
	}
}
