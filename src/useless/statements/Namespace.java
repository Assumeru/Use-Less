package useless.statements;

import useless.program.Program;
import useless.program.RunnableStatement;

public class Namespace implements RunnableStatement {
	private static final long serialVersionUID = 5542448222858613066L;
	private String namespace;

	public Namespace(String namespace) {
		this.namespace = namespace;
	}

	@Override
	public void run(Program program) {
		program.setCurrentNamespace(namespace);
	}
}
