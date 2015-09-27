package useless.program;

import java.io.Serializable;

import useless.data.Variable;

public class VariablePointer implements Serializable {
	private static final long serialVersionUID = -1876811312437421880L;
	private transient Namespace namespace;
	private int pos;
	private int length;

	public VariablePointer(int pos, int length, Namespace namespace) {
		this.length = length;
		this.pos = pos;
		this.namespace = namespace;
	}

	public byte[] getValue() {
		return namespace.getProgram().getMemory().get(pos, length);
	}

	public void setValue(Variable value) {
		namespace.getProgram().getMemory().put(pos, value.getValue());
	}

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public int length() {
		return length;
	}
}
