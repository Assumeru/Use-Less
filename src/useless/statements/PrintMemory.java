package useless.statements;

import java.util.Arrays;

import useless.data.Memory;
import useless.program.Program;
import useless.program.RunnableStatement;

public class PrintMemory implements RunnableStatement {
	private static final long serialVersionUID = -3738052452984770416L;

	@Override
	public void run(Program program) {
		Memory memory = program.getMemory();
		program.getIO().out.println(Arrays.toString(memory.get(0, memory.size())));
	}
}
