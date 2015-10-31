package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.variables.Variable;

public class DivisionToken extends VariableBinaryToken {
	public DivisionToken() {
		super("/", new DivisionParsedToken());
	}

	public static class DivisionParsedToken extends ParsedVariableToken {
		private static final long serialVersionUID = 3918345172596911990L;

		public DivisionParsedToken() {
			super(OPERATION_MULT_DIV);
		}

		@Override
		protected ParsedItem getResult(Variable first, Variable second) throws ParseException {
			return (ParsedItem) first.divide(second);
		}
	}
}
