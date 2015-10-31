package useless.tokens;

import java.util.List;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.PrintMemoryStatement;

public class PrintMemoryToken extends SimpleToken {
	public PrintMemoryToken() {
		super("printMemory", new ParsedPrintMemoryToken());
	}

	public static class ParsedPrintMemoryToken extends ParsedToken {
		private static final long serialVersionUID = -1156947249090548834L;

		public ParsedPrintMemoryToken() {
			super(OPERATION_FUNCTION);
		}

		@Override
		public boolean parseStatement(List<ParsedItem> tokens, int index) throws ParseException {
			tokens.set(index, new PrintMemoryStatement());
			return true;
		}
	}
}
