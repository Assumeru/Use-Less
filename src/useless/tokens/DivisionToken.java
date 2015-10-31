package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.variables.Variable;

public class DivisionToken extends VariableBinaryToken {
	public DivisionToken() {
		super("/", new DivisionParsedToken());
	}

	public static class DivisionParsedToken extends VariableParsedToken {
		private static final long serialVersionUID = -203535892236835772L;

		public DivisionParsedToken() {
			super(11);
		}

		@Override
		protected ParsedItem getResult(Variable first, Variable second) throws ParseException {
			return (ParsedItem) first.divide(second);
		}
	}
}
