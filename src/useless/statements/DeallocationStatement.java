package useless.statements;

import useless.program.Program;
import useless.tokens.NameToken.Name;

public class DeallocationStatement implements Statement {
	private static final long serialVersionUID = 2690635379263303679L;
	private Name name;

	public DeallocationStatement(Name name) {
		this.name = name;
	}

	@Override
	public void run(Program program) {
		if(name instanceof Statement) {
			((Statement) name).run(program);
		}
		program.getCurrentNamespace().deallocateVariable(name.getName());
	}
}
