package useless.parser;

import useless.exceptions.ParseException;

public interface AfterParseStatement extends Statement {
	public LineStatementResult onAfterParse(Parser parser, String line, int index) throws ParseException;
}
