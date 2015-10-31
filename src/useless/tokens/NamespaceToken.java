package useless.tokens;

import java.util.List;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.statements.NamespaceStatement;
import useless.tokens.NameToken.Name;

public class NamespaceToken extends SimpleToken {
	public NamespaceToken() {
		super("@", new ParsedNamespaceToken());
	}

	public static class ParsedNamespaceToken extends ParsedToken {
		private static final long serialVersionUID = 2185522931735451865L;

		public ParsedNamespaceToken() {
			super(OPERATION_CONSTRUCT);
		}

		@Override
		public boolean parseStatement(List<ParsedItem> tokens, int index) throws ParseException {
			if(index + 1 < tokens.size()) {
				ParsedItem name = tokens.get(index + 1);
				if(name instanceof Name) {
					tokens.remove(index + 1);
					tokens.set(index, new NamespaceStatement(((Name) name).getName()));
					return true;
				}
			}
			tokens.set(index, new NamespaceStatement(""));
			return true;
		}
	}
}
