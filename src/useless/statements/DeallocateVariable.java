package useless.statements;

import useless.program.Program;
import useless.program.RunStatement;

public class DeallocateVariable implements RunStatement {
	private static final long serialVersionUID = -3198583652671641213L;
	private String name;

	public DeallocateVariable(String name) {
		this.name = name;
	}

	@Override
	public void run(Program program) {
		program.getCurrentNamespace().deallocateVariable(name);
	}
}
