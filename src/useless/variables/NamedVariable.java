package useless.variables;

import useless.program.Program;
import useless.program.VariablePointer;
import useless.statements.Statement;

public class NamedVariable implements Variable, Statement {
	private static final long serialVersionUID = 5515008468055703762L;
	private String name;
	private VariablePointer variable;

	public NamedVariable(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public void run(Program program) {
		variable = program.getCurrentNamespace().getVariable(name);
	}

	@Override
	public byte[] getValue() {
		return variable.getValue();
	}

	public void setValue(Variable value) {
		variable.setValue(value);
	}

	@Override
	public Variable multiply(Variable value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable add(Variable value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable subtract(Variable value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable divide(Variable value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		if(variable == null) {
			return "VAR["+name+"]";
		}
		return new String(getValue());
	}
}
