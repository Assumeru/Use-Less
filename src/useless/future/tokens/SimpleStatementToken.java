package useless.future.tokens;

import useless.future.statements.Statement;

public abstract class SimpleStatementToken extends StatementToken {
	private String token;

	public SimpleStatementToken(String token, int precedence) {
		super(precedence);
		this.token = token;
	}

	public abstract Statement getStatement();

	@Override
	public ConsumedToken<Statement> consume(String input, int index) {
		if(input.startsWith(token, index)) {
			return new ConsumedToken<Statement>(token.length(), getStatement());
		}
		return null;
	}
}
