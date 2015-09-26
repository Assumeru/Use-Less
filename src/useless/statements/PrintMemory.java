package useless.statements;

import java.util.Arrays;

import useless.data.Memory;
import useless.program.Program;
import useless.program.RunStatement;

public class PrintMemory implements RunStatement {
	private static final long serialVersionUID = -3738052452984770416L;

	@Override
	public void run(Program program) {
		Memory memory = program.getMemory();
		if(memory.size() == 0) {
			program.getIO().out.println("[]");
		} else {
			program.getIO().out.println(Arrays.toString(memory.get(0, memory.size())));
		}
	}
}
