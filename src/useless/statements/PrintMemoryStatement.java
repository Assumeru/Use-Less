package useless.statements;

import java.util.Arrays;

import useless.program.Program;

public class PrintMemoryStatement implements Statement {
	private static final long serialVersionUID = -2286109384808194368L;

	@Override
	public void run(Program program) {
		program.getIO().out.print(Arrays.toString(program.getMemory().get(0, program.getMemory().size())));
	}
}
