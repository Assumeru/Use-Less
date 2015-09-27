package useless.statements;

import java.util.List;

import useless.data.Variable;
import useless.exceptions.ParseException;
import useless.parser.AfterParseStatement;
import useless.parser.LineStatementResult;
import useless.parser.Parser;
import useless.parser.Statement;
import useless.program.RunStatement;

public class Assignment implements RunStatement, AfterParseStatement {
	private static final long serialVersionUID = -1514915983977336424L;
	private int token;
	private RuntimeAssignment result;

	public Assignment(int token) {
		this.token = token;
	}

	@Override
	public LineStatementResult onAfterParse(Parser parser, String line, int index) throws ParseException {
		boolean delete = false;
		List<Statement> parsedStatements = parser.getParsedStatements();
		if(result == null) {
			for(int i = index - 1; i < parsedStatements.size(); i++) {
				Statement statement = parsedStatements.get(i);
				if(statement instanceof Variable) {
					result = new RuntimeAssignment((Variable) statement);
					parsedStatements.set(i, result);
					break;
				} else if(statement instanceof RunStatement) {
					break;
				}
			}
			if(result == null) {
				throw new ParseException("Assignment expects a variable to assign to", token);
			}
		}
		for(int i = index + 1; i < parsedStatements.size(); i++) {
			Statement statement = parsedStatements.get(i);
			if(statement instanceof Variable) {
				delete = true;
				result.setVar((Variable) statement);
				parsedStatements.remove(i);
				break;
			} else if(statement instanceof RunStatement) {
				throw new ParseException("Assignment expects one argument", token);
			}
		}
		return new LineStatementResult(line, delete);
	}
}
