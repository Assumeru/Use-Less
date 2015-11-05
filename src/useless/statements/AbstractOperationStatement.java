package useless.statements;

import useless.program.Program;
import useless.tokens.IntegerToken;
import useless.variables.StatementVariable;
import useless.variables.Variable;

public abstract class AbstractOperationStatement extends StatementVariable {
	private static final long serialVersionUID = -6028069683619183346L;
	protected Variable lhs;
	protected Variable rhs;
	private byte[] value;

	public AbstractOperationStatement(Variable lhs, Variable rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}

	@Override
	public byte[] getValue() {
		return value;
	}

	@Override
	public void run(Program program) {
		if(lhs instanceof Statement) {
			((Statement) lhs).run(program);
		}
		if(rhs instanceof Statement) {
			((Statement) rhs).run(program);
		}
		if(lhs.getValue().length <= 8 && rhs.getValue().length <= 8) {
			value = IntegerToken.createVariable(numericOperation(Variable.getLongValue(lhs), Variable.getLongValue(rhs))).getValue();
		} else {
			value = binaryOperation(lhs.getValue(), rhs.getValue());
		}
	}

	protected abstract long numericOperation(long lhs, long rhs);

	protected byte[] binaryOperation(byte[] lhs, byte[] rhs) {
		throw new UnsupportedOperationException();
	}
}
