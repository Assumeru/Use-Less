package useless.tokens;

import java.util.List;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.PrintStatement;
import useless.variables.Variable;

public class PrintToken extends SimpleToken {
	public PrintToken() {
		super("print", new ParsedPrintToken());
	}

	public static class ParsedPrintToken extends ParsedToken {
		private static final long serialVersionUID = 1387336072284754706L;

		public ParsedPrintToken() {
			super(0);
		}

		@Override
		public boolean parseStatement(List<ParsedItem> tokens, int index) throws ParseException {
			if(index + 1 < tokens.size()) {
				ParsedItem variable = tokens.get(index + 1);
				if(variable instanceof Variable) {
					tokens.remove(index + 1);
					tokens.set(index, new PrintStatement((Variable) variable));
					return true;
				}
			}
			throw new ParseException("print expects a variable", index);
		}
	}
}
