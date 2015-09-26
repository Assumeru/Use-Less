package useless.parser;

public interface BeforeParseStatement extends Statement {
	public LineStatementResult onBeforeParse(Parser parser, String line);
}
