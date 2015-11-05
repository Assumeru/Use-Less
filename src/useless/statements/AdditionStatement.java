package useless.statements;

import useless.variables.Variable;

public class AdditionStatement extends AbstractOperationStatement {
	private static final long serialVersionUID = -5088742153835720335L;

	public AdditionStatement(Variable lhs, Variable rhs) {
		super(lhs, rhs);
	}

	@Override
	protected long numericOperation(long lhs, long rhs) {
		return lhs + rhs;
	}
}
