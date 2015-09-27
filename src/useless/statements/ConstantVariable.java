package useless.statements;

import useless.data.Variable;
import useless.program.RunStatement;

public abstract class ConstantVariable implements RunStatement, Variable {
	private static final long serialVersionUID = -8385948905386803889L;
	private byte[] value;

	public ConstantVariable(byte[] value) {
		this.value = value;
	}

	@Override
	public byte[] getValue() {
		return value;
	}

	@Override
	public void setValue(Variable variable) {
		value = variable.getValue();
	}
}
