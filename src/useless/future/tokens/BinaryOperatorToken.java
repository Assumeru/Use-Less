package useless.future.tokens;

public abstract class BinaryOperatorToken extends SimpleStatementToken {
	public BinaryOperatorToken(String token, int precedence) {
		super(token, precedence);
	}
}
