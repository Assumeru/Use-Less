package useless.statements;

import useless.variables.Variable;

public class SubtractionStatement extends AbstractOperationStatement {
	private static final long serialVersionUID = -5590318730341298501L;

	public SubtractionStatement(Variable lhs, Variable rhs) {
		super(lhs, rhs);
	}

	@Override
	protected long numericOperation(long lhs, long rhs) {
		return lhs - rhs;
	}
}
