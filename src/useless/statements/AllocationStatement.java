package useless.statements;

import useless.program.Program;
import useless.tokens.NameToken.Name;
import useless.variables.NamedVariable;

public class AllocationStatement extends NamedVariable {
	private static final long serialVersionUID = 7883838055202467929L;
	private int size;

	public AllocationStatement(Name name, int size) {
		super(name);
		this.size = size;
	}

	protected void setSize(int size) {
		this.size = size;
	}

	@Override
	public void run(Program program) {
		if(getName() instanceof Statement) {
			((Statement) getName()).run(program);
		}
		program.getCurrentNamespace().allocateVariable(getName().getName(), size);
		super.run(program);
	}
}
