package useless.statements;

import useless.variables.Variable;

public class DivisionStatement extends AbstractOperationStatement {
	private static final long serialVersionUID = -2646854068508502604L;

	public DivisionStatement(Variable lhs, Variable rhs) {
		super(lhs, rhs);
	}

	@Override
	protected long numericOperation(long lhs, long rhs) {
		return lhs / rhs;
	}
}
