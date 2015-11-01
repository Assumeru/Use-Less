package useless.tokens;

import java.util.List;

import useless.exceptions.ParseException;
import useless.parser.ConsumedToken;
import useless.parser.ParsedItem;
import useless.variables.NamedVariable;

public class NameToken implements Token {
	@Override
	public ConsumedToken consume(String input, int index) {
		if(!Character.isLetterOrDigit(input.charAt(index))) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for(int i = index; i < input.length(); i++) {
			if(!Character.isLetterOrDigit(input.charAt(i))) {
				break;
			}
			sb.append(input.charAt(i));
		}
		return new ConsumedToken(sb.length(), new Name(sb.toString()));
	}

	public static class Name extends ParsedToken {
		private static final long serialVersionUID = -4309881036312544748L;
		private String name;

		public Name(String name) {
			super(OPERATION_VARIABLE);
			this.name = name;
		}

		@Override
		public boolean parseStatement(List<ParsedItem> tokens, int index) throws ParseException {
			tokens.set(index, new NamedVariable(this));
			return true;
		}

		public String getName() {
			return name;
		}

		@Override
		public String toString() {
			return "NAME["+name+"]";
		}
	}
}
