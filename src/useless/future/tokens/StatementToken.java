package useless.future.tokens;

import useless.future.statements.Statement;

public abstract class StatementToken extends Token<StatementToken, Statement> {
	public StatementToken(int precedence) {
		super(precedence);
	}
}
