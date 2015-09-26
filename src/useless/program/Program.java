package useless.program;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import useless.data.IO;
import useless.data.Memory;
import useless.parser.Statement;

public class Program implements Runnable, Serializable {
	private static final long serialVersionUID = 1106161002816336835L;
	private List<RunStatement> statements;
	private transient IO io;
	private Memory memory;
	private Map<String, Namespace> namespaces;
	private Namespace currentNamespace;

	public Program(List<Statement> statements) {
		this.statements = new ArrayList<RunStatement>();
		for(Statement statement : statements) {
			if(statement instanceof RunStatement) {
				this.statements.add((RunStatement) statement);
			}
		}
	}

	@Override
	public void run() {
		namespaces = new HashMap<String, Namespace>();
		setCurrentNamespace("");
		for(RunStatement statement : statements) {
			statement.run(this);
		}
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

	public IO getIO() {
		return io;
	}

	public void setIO(IO io) {
		this.io = io;
	}

	public void setCurrentNamespace(String name) {
		if(namespaces.containsKey(name)) {
			currentNamespace = namespaces.get(name);
		} else {
			currentNamespace = new Namespace(name, this);
			namespaces.put(name, currentNamespace);
		}
	}

	public Namespace getCurrentNamespace() {
		return currentNamespace;
	}

	public Map<String, Namespace> getNamespaces() {
		return namespaces;
	}
}
