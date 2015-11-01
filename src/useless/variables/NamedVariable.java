package useless.variables;

import useless.program.Program;
import useless.program.VariablePointer;
import useless.statements.Statement;
import useless.tokens.NameToken.Name;

public class NamedVariable implements Variable, Statement {
	private static final long serialVersionUID = 5515008468055703762L;
	private Name name;
	private VariablePointer variable;

	public NamedVariable(Name name) {
		this.name = name;
	}

	public Name getName() {
		return name;
	}

	@Override
	public void run(Program program) {
		if(name instanceof Statement) {
			((Statement) name).run(program);
		}
		variable = program.getCurrentNamespace().getVariable(name.getName());
		if(variable == null) {
			throw new RuntimeException("variable \""+name.getName()+"\" @ \""+program.getCurrentNamespace().getName()+"\" is not defined");
		}
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
