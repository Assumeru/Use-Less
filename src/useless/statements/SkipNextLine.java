package useless.statements;

import useless.parser.BeforeParseStatement;
import useless.parser.LineStatementResult;
import useless.parser.Parser;

public class SkipNextLine implements BeforeParseStatement {
	@Override
	public LineStatementResult onBeforeParse(Parser parser, String line) {
		return new LineStatementResult(null, true);
	}
}
