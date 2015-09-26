package useless.parser;

public interface AfterParseStatement extends Statement {
	public LineStatementResult onAfterParse(Parser parser, String line);
}
