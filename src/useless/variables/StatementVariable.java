package useless.variables;

import useless.statements.AdditionStatement;
import useless.statements.DivisionStatement;
import useless.statements.MultiplicationStatement;
import useless.statements.Statement;
import useless.statements.SubtractionStatement;

public abstract class StatementVariable implements Variable, Statement {
	private static final long serialVersionUID = -6399751910872254444L;

	@Override
	public Variable multiply(Variable value) {
		return new MultiplicationStatement(this, value);
	}

	@Override
	public Variable add(Variable value) {
		return new AdditionStatement(this, value);
	}

	@Override
	public Variable subtract(Variable value) {
		return new SubtractionStatement(this, value);
	}

	@Override
	public Variable divide(Variable value) {
		return new DivisionStatement(this, value);
	}
}
