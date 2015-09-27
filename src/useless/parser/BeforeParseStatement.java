package useless.parser;

import useless.exceptions.ParseException;

public interface BeforeParseStatement extends Statement {
	public LineStatementResult onBeforeParse(Parser parser, String line) throws ParseException;
}
