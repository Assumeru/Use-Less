package useless.statements;

import useless.variables.Variable;

public class MultiplicationStatement extends AbstractOperationStatement {
	private static final long serialVersionUID = 508590055136330736L;

	public MultiplicationStatement(Variable lhs, Variable rhs) {
		super(lhs, rhs);
	}

	@Override
	protected long numericOperation(long lhs, long rhs) {
		return lhs * rhs;
	}
}
