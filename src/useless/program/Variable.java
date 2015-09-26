package useless.program;

import java.io.Serializable;

public class Variable implements Serializable {
	private static final long serialVersionUID = -1876811312437421880L;
	private transient Namespace namespace;
	private int size;
	private int start;

	public Variable(int start, int size, Namespace namespace) {
		this.size = size;
		this.start = start;
		this.namespace = namespace;
	}

	public byte[] getValue() {
		return namespace.getProgram().getMemory().get(start, size);
	}

	public void setValue(byte[] value) {
		namespace.getProgram().getMemory().put(size, value);
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getSize() {
		return size;
	}
}
