package useless.program;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Namespace implements Serializable {
	private static final long serialVersionUID = 1684644219303011208L;
	private Map<String, VariablePointer> variables;
	private String name;
	private transient Program program;

	public Namespace(String name, Program program) {
		this.name = name;
		this.program = program;
		variables = new HashMap<String, VariablePointer>();
	}

	public String getName() {
		return name;
	}

	public VariablePointer getVariable(String name) {
		return variables.get(name);
	}

	public void allocateVariable(String name, int size) {
		variables.put(name, new VariablePointer(program.getMemory().allocate(size), size, this));
	}

	public void deallocateVariable(String name) {
		VariablePointer var = variables.get(name);
		if(var != null) {
			variables.remove(name);
			if(var.length() != 0) {
				program.getMemory().free(var.getPos(), var.length());
				for(Namespace namespace : program.getNamespaces().values()) {
					for(VariablePointer variable : namespace.variables.values()) {
						if(variable.getPos() >= var.getPos()) {
							variable.setPos(variable.getPos() - var.length());
						}
					}
				}
			}
		}
	}

	public Program getProgram() {
		return program;
	}
}
