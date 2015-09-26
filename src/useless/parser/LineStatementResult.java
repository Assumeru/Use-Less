package useless.parser;

public class LineStatementResult {
	private String line;
	private boolean deleteStatement;

	public LineStatementResult(String line, boolean deleteStatement) {
		this.line = line;
		this.deleteStatement = deleteStatement;
	}

	public String getLine() {
		return line;
	}

	public boolean deleteStatement() {
		return deleteStatement;
	}
}
