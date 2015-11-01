package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.LongAllocationStatement;
import useless.tokens.NameToken.Name;

public class LongAllocationToken extends NamePrefixToken {
	public LongAllocationToken() {
		super("long", new ParsedLongAllocationToken());
	}

	public static class ParsedLongAllocationToken extends ParsedNamePrefixToken {
		private static final long serialVersionUID = 8130416728786541273L;

		public ParsedLongAllocationToken() {
			super(OPERATION_CONSTRUCT);
		}

		@Override
		protected ParsedItem getResult(Name variable) throws ParseException {
			return new LongAllocationStatement(variable);
		}
	}
}
