package useless.statements;

import useless.program.Program;
import useless.variables.Variable;

public class PrintStatement implements Statement {
	private static final long serialVersionUID = -6274370333669417663L;
	private Variable variable;

	public PrintStatement(Variable variable) {
		this.variable = variable;
	}

	@Override
	public void run(Program program) {
		if(variable instanceof Statement) {
			((Statement) variable).run(program);
		}
		program.getIO().out.print(variable.toString());
	}
}
