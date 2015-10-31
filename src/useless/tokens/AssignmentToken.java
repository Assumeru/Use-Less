package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.AssignmentStatement;
import useless.variables.NamedVariable;
import useless.variables.Variable;

public class AssignmentToken extends VariableBinaryToken {
	public AssignmentToken() {
		super("=", new ParsedAssignmentToken());
	}

	public static class ParsedAssignmentToken extends ParsedVariableToken {
		private static final long serialVersionUID = 5112084090887410464L;

		public ParsedAssignmentToken() {
			super(OPERATION_FUNCTION);
		}

		@Override
		protected ParsedItem getResult(Variable first, Variable second) throws ParseException {
			if(first instanceof NamedVariable) {
				return new AssignmentStatement((NamedVariable) first, second);
			}
			throw new ParseException("= can only assign to variables", -1);
		}
	}
}
