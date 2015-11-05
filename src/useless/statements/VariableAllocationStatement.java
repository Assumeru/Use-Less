package useless.statements;

import useless.program.Program;
import useless.tokens.NameToken.Name;
import useless.variables.Variable;

public class VariableAllocationStatement extends AllocationStatement {
	private static final long serialVersionUID = 9172429180631371012L;
	private Variable size;

	public VariableAllocationStatement(Name name, Variable size) {
		super(name, 0);
		this.size = size;
	}

	@Override
	public void run(Program program) {
		if(size instanceof Statement) {
			((Statement) size).run(program);
		}
		setSize(Variable.getIntValue(size));
		super.run(program);
	}
}
