package useless.program;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Namespace implements Serializable {
	private static final long serialVersionUID = 1684644219303011208L;
	private Map<String, Variable> variables;
	private String name;
	private transient Program program;

	public Namespace(String name, Program program) {
		this.name = name;
		this.program = program;
		variables = new HashMap<String, Variable>();
	}

	public String getName() {
		return name;
	}

	public void allocateVariable(String name, int size) {
		variables.put(name, new Variable(program.getMemory().allocate(size), size, this));
	}

	public void deallocateVariable(String name) {
		Variable var = variables.get(name);
		if(var != null) {
			variables.remove(name);
			if(var.getSize() != 0) {
				program.getMemory().free(var.getStart(), var.getSize());
				for(Namespace namespace : program.getNamespaces().values()) {
					for(Variable variable : namespace.variables.values()) {
						if(variable.getStart() >= var.getStart()) {
							variable.setStart(variable.getStart() - var.getSize());
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
