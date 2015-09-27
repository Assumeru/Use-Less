package useless.statements;

import useless.data.Variable;
import useless.program.Program;
import useless.program.RunnableStatement;

public class RuntimeDeallocation implements RunnableStatement {
	private static final long serialVersionUID = -3198583652671641213L;
	private Variable name;

	public RuntimeDeallocation(Variable name) {
		this.name = name;
	}

	@Override
	public void run(Program program) {
		if(name instanceof RunnableStatement) {
			((RunnableStatement) name).run(program);
		}
		program.getCurrentNamespace().deallocateVariable(new String(name.getValue()));
	}
}
