package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.IntegerAllocationStatement;
import useless.tokens.NameToken.Name;

public class IntegerAllocationToken extends PrefixToken {
	public IntegerAllocationToken() {
		super("int", new ParsedIntegerAllocationToken());
	}

	public static class ParsedIntegerAllocationToken extends ParsedPrefixToken {
		private static final long serialVersionUID = -4559178186796011706L;

		public ParsedIntegerAllocationToken() {
			super(OPERATION_CONSTRUCT);
		}

		@Override
		protected ParsedItem getResult(ParsedItem variable) throws ParseException {
			if(variable instanceof Name) {
				return new IntegerAllocationStatement(((Name) variable).getName());
			}
			throw new ParseException("int allocation expects a name", -1);
		}
	}
}
