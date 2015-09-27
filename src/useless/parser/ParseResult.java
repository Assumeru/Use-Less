package useless.parser;

public class ParseResult {
	private Statement statement;
	private int tokensConsumed;

	public ParseResult(Statement statement, int tokensConsumed) {
		this.statement = statement;
		this.tokensConsumed = tokensConsumed;
	}

	public ParseResult(Statement statement) {
		this(statement, 0);
	}

	public Statement getStatement() {
		return statement;
	}

	public int getExtraTokensConsumed() {
		return tokensConsumed;
	}
}
