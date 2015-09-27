package useless.statements;

import java.util.List;

import useless.data.Variable;
import useless.exceptions.ParseException;
import useless.parser.AfterParseStatement;
import useless.parser.LineStatementResult;
import useless.parser.Parser;
import useless.parser.Statement;
import useless.program.RunStatement;

public class DeallocateVariable implements RunStatement, AfterParseStatement {
	private static final long serialVersionUID = -1571239973032250433L;
	private int index;

	public DeallocateVariable(int index) {
		this.index = index;
	}

	@Override
	public LineStatementResult onAfterParse(Parser parser, String line, int index) throws ParseException {
		List<Statement> parsedStatements = parser.getParsedStatements();
		for(int i = index + 1; i < parsedStatements.size(); i++) {
			Statement statement = parsedStatements.get(i);
			if(statement instanceof Variable) {
				parsedStatements.remove(i);
				parsedStatements.set(index, new RuntimeDeallocation((Variable) statement));
			} else if(statement instanceof RunStatement) {
				throw new ParseException("Deallocation expects one variable arguments [name]", this.index);
			}
		}
		return new LineStatementResult(line, false);
	}
}
