package useless.statements;

import useless.program.Program;
import useless.variables.NamedVariable;
import useless.variables.Variable;

public class AssignmentStatement extends NamedVariable {
	private static final long serialVersionUID = 6910399695088019745L;
	private Variable value;
	private NamedVariable variable;

	public AssignmentStatement(NamedVariable variable, Variable value) {
		super(variable.getName());
		this.variable = variable;
		this.value = value;
	}

	@Override
	public void run(Program program) {
		variable.run(program);
		super.run(program);
		if(value instanceof Statement) {
			((Statement) value).run(program);
		}
		setValue(value);
	}
}
