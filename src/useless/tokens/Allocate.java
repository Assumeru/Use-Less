package useless.tokens;

import java.util.Iterator;

import useless.exceptions.ParseException;
import useless.parser.ParseResult;
import useless.parser.Parser;
import useless.parser.Statement;
import useless.parser.TokenParser;
import useless.program.RunStatement;
import useless.statements.AllocateVariable;

public class Allocate implements TokenParser {
	@Override
	public boolean matches(Parser parser, String token) {
		return token.equals("+") && noPreviousRunStatements(parser);
	}

	private boolean noPreviousRunStatements(Parser parser) {
		for(Iterator<Statement> it = parser.getLineStatements().descendingIterator(); it.hasNext();) {
			if(it.next() instanceof RunStatement) {
				return false;
			}
		}
		return true;
	}

	@Override
	public ParseResult parse(Parser parser, int index, String[] tokens) throws ParseException {
		if(index + 2 >= tokens.length) {
			throw new ParseException("Allocation expects two arguments: + [name] [size]", index);
		}
		return new ParseResult(new AllocateVariable(index));
	}
}
