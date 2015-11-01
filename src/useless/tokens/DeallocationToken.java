package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.DeallocationStatement;
import useless.tokens.NameToken.Name;

public class DeallocationToken extends NamePrefixToken {
	public DeallocationToken() {
		super("~", new ParsedDeallocationToken());
	}

	public static class ParsedDeallocationToken extends ParsedNamePrefixToken {
		private static final long serialVersionUID = 6267371429325706632L;

		public ParsedDeallocationToken() {
			super(OPERATION_CONSTRUCT);
		}

		@Override
		protected ParsedItem getResult(Name variable) throws ParseException {
			return new DeallocationStatement(variable);
		}
	}
}
