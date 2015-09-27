package useless.statements;

import useless.data.Variable;
import useless.program.Program;
import useless.program.RunnableStatement;

public class RuntimeAllocation implements RunnableStatement {
	private static final long serialVersionUID = -4185306915149605800L;
	private Variable name;
	private Variable size;

	public RuntimeAllocation(Variable name, Variable size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public void run(Program program) {
		if(name instanceof RunnableStatement) {
			((RunnableStatement) name).run(program);
		}
		if(size instanceof RunnableStatement) {
			((RunnableStatement) size).run(program);
		}
		program.getCurrentNamespace().allocateVariable(new String(name.getValue()), IntegerVariable.fromBytes(size.getValue()));
	}

}
