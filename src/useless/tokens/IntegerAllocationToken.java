package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.IntegerAllocationStatement;
import useless.tokens.NameToken.Name;

public class IntegerAllocationToken extends NamePrefixToken {
	public IntegerAllocationToken() {
		super("int", new ParsedIntegerAllocationToken());
	}

	public static class ParsedIntegerAllocationToken extends ParsedNamePrefixToken {
		private static final long serialVersionUID = -4559178186796011706L;

		public ParsedIntegerAllocationToken() {
			super(OPERATION_CONSTRUCT);
		}

		@Override
		protected ParsedItem getResult(Name variable) throws ParseException {
			return new IntegerAllocationStatement(variable);
		}
	}
}
