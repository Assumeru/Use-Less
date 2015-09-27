package useless.statements;

import useless.data.Variable;
import useless.program.Namespace;
import useless.program.Program;
import useless.program.RunnableStatement;
import useless.program.VariablePointer;

public class MemoryVariable implements RunnableStatement, Variable {
	private static final long serialVersionUID = -6470698274683642791L;
	private String name;
	private String namespace;
	private VariablePointer variable;

	public MemoryVariable(String name) {
		this.name = name;
	}

	@Override
	public byte[] getValue() {
		if(variable == null) {
			throw new RuntimeException("Variable "+name+" is undefined in "+namespace);
		}
		return variable.getValue();
	}

	@Override
	public void setValue(Variable value) {
		if(variable == null) {
			throw new RuntimeException("Variable "+name+" is undefined in "+namespace);
		}
		variable.setValue(value);
	}

	@Override
	public void run(Program program) {
		Namespace namespace = program.getCurrentNamespace();
		this.namespace = namespace.getName();
		variable = namespace.getVariable(name);
	}
}
