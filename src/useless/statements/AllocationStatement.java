package useless.statements;

import useless.program.Program;
import useless.variables.NamedVariable;

public class AllocationStatement extends NamedVariable {
	private static final long serialVersionUID = 7883838055202467929L;
	private int size;

	public AllocationStatement(String name, int size) {
		super(name);
		this.size = size;
	}

	@Override
	public void run(Program program) {
		program.getCurrentNamespace().allocateVariable(getName(), size);
		super.run(program);
	}
}
