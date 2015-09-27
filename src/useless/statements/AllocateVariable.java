package useless.statements;

import java.util.List;

import useless.data.Variable;
import useless.exceptions.ParseException;
import useless.parser.AfterParseStatement;
import useless.parser.LineStatementResult;
import useless.parser.Parser;
import useless.parser.Statement;
import useless.program.RunStatement;

public class AllocateVariable implements RunStatement, AfterParseStatement {
	private static final long serialVersionUID = -3017324321000424034L;
	private int index;
	private Variable name;
	private Variable size;

	public AllocateVariable(int index) {
		this.index = index;
	}

	@Override
	public LineStatementResult onAfterParse(Parser parser, String line, int index) throws ParseException {
		List<Statement> parsedStatements = parser.getParsedStatements();
		for(int i = index + 1; i < parsedStatements.size(); i++) {
			Statement statement = parsedStatements.get(i);
			if(statement instanceof Variable) {
				parsedStatements.remove(i);
				i--;
				if(name == null) {
					name = (Variable) statement;
				} else if(size == null) {
					size = (Variable) statement;
					parsedStatements.set(index, new RuntimeAllocation(name, size));
					break;
				}
			} else if(statement instanceof RunStatement) {
				throw new ParseException("Allocation expects two variable arguments [name] [size]", this.index);
			}
		}
		return new LineStatementResult(line, false);
	}
}
