package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.AllocationStatement;
import useless.statements.VariableAllocationStatement;
import useless.tokens.NameToken.Name;
import useless.variables.AbstractIntegerVariable;
import useless.variables.ParsedVariable;
import useless.variables.Variable;

public class AllocationToken extends BinaryToken {
	public AllocationToken() {
		super("<-", new ParsedAllocationToken());
	}

	public static class ParsedAllocationToken extends ParsedBinaryToken {
		private static final long serialVersionUID = -5530491666058099629L;

		public ParsedAllocationToken() {
			super(OPERATION_CONSTRUCT);
		}

		@Override
		protected ParsedItem getResult(ParsedItem first, ParsedItem second) throws ParseException {
			if(!(first instanceof Name)) {
				throw new ParseException("allocation expects a name", -1);
			} else if(!(second instanceof Variable)) {
				throw new ParseException("allocation expects a variable", -1);
			} else if(second instanceof ParsedVariable) {
				Name name = (Name) first;
				int size;
				if(second instanceof AbstractIntegerVariable) {
					size = (int)((AbstractIntegerVariable) second).getLongValue();
				} else {
					size = Variable.getIntValue((ParsedVariable) second);
				}
				return new AllocationStatement(name, size);
			}
			return new VariableAllocationStatement((Name) first, (Variable) second);
		}
	}
}
