package useless.statements;

import useless.program.Program;
import useless.program.RunStatement;

public class AllocateVariable implements RunStatement {
	private static final long serialVersionUID = -3017324321000424034L;
	private int size;
	private String name;

	public AllocateVariable(int size, String name) {
		this.size = size;
		this.name = name;
	}

	@Override
	public void run(Program program) {
		program.getCurrentNamespace().allocateVariable(name, size);
	}
}
