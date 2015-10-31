package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.variables.Variable;

public class AdditionToken extends VariableBinaryToken {
	public AdditionToken() {
		super("+", new AdditionParsedToken());
	}

	public static class AdditionParsedToken extends ParsedVariableToken {
		private static final long serialVersionUID = -203535892236835772L;

		public AdditionParsedToken() {
			super(OPERATION_ADD_SUB);
		}

		@Override
		protected ParsedItem getResult(Variable first, Variable second) throws ParseException {
			return (ParsedItem) first.add(second);
		}
	}
}
