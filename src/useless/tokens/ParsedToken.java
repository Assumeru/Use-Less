package useless.tokens;

import java.util.List;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;

public abstract class ParsedToken implements ParsedItem {
	public static final int OPERATION_FUNCTION = 0;
	public static final int OPERATION_VARIABLE = 100;
	public static final int OPERATION_CONSTRUCT = 200;
	public static final int OPERATION_ADD_SUB = 10;
	public static final int OPERATION_MULT_DIV = 11;
	private static final long serialVersionUID = -5983550695235574350L;
	private int precedence;

	public ParsedToken(int precedence) {
		this.precedence = precedence;
	}

	public int getPrecedence() {
		return precedence;
	}

	public abstract boolean parseStatement(List<ParsedItem> tokens, int index) throws ParseException;
}
