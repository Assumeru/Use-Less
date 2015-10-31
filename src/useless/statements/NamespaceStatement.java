package useless.statements;

import useless.program.Program;

public class NamespaceStatement implements Statement {
	private static final long serialVersionUID = 3686175177082612108L;
	private String name;

	public NamespaceStatement(String name) {
		this.name = name;
	}

	@Override
	public void run(Program program) {
		program.setCurrentNamespace(name);
	}
}
