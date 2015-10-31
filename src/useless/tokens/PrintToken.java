package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.PrintStatement;
import useless.variables.Variable;

public class PrintToken extends PrefixToken {
	public PrintToken() {
		super("print", new ParsedPrintToken());
	}

	public static class ParsedPrintToken extends ParsedPrefixToken {
		private static final long serialVersionUID = 1387336072284754706L;

		public ParsedPrintToken() {
			super(OPERATION_FUNCTION);
		}

		@Override
		protected ParsedItem getResult(ParsedItem variable) throws ParseException {
			if(variable instanceof Variable) {
				return new PrintStatement((Variable) variable);
			}
			return null;
		}
	}
}
