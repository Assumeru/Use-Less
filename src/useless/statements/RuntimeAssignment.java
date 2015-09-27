package useless.statements;

import useless.data.Variable;
import useless.program.Program;
import useless.program.RunnableStatement;

public class RuntimeAssignment implements RunnableStatement, Variable {
	private static final long serialVersionUID = -6855056713093605633L;
	private Variable target;
	private Variable value;

	public RuntimeAssignment(Variable target) {
		this.target = target;
	}

	public void setVar(Variable value) {
		this.value = value;
	}

	@Override
	public void run(Program program) {
		if(target instanceof RunnableStatement) {
			((RunnableStatement) target).run(program);
		}
		if(value instanceof RunnableStatement) {
			((RunnableStatement) value).run(program);
		}
		target.setValue(value);
	}

	@Override
	public byte[] getValue() {
		return target.getValue();
	}

	@Override
	public void setValue(Variable value) {
		target.setValue(value);
	}
}
