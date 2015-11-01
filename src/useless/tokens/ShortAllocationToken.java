package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.ShortAllocationStatement;
import useless.tokens.NameToken.Name;

public class ShortAllocationToken extends NamePrefixToken {
	public ShortAllocationToken() {
		super("short", new ParsedShortAllocationToken());
	}

	public static class ParsedShortAllocationToken extends ParsedNamePrefixToken {
		private static final long serialVersionUID = 8898023674809187699L;

		public ParsedShortAllocationToken() {
			super(OPERATION_CONSTRUCT);
		}

		@Override
		protected ParsedItem getResult(Name variable) throws ParseException {
			return new ShortAllocationStatement(variable);
		}
	}
}
