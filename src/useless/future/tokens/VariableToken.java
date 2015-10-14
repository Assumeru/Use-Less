package useless.future.tokens;

import useless.future.Variable;

public abstract class VariableToken extends Token<VariableToken, Variable> {
	public VariableToken(int precedence) {
		super(precedence);
	}
}
