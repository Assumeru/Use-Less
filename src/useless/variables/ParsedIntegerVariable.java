package useless.variables;

public class ParsedIntegerVariable extends AbstractIntegerVariable {
	private static final long serialVersionUID = -5364334210263703650L;
	private int value;

	public ParsedIntegerVariable(int value) {
		this.value = value;
	}

	@Override
	public byte[] getValue() {
		return new byte[] {
			(byte) (value & 0xFF),
			(byte) ((value >> 8) & 0xFF),
			(byte) ((value >> 16) & 0xFF),
			(byte) ((value >> 24) & 0xFF)
		};
	}

	@Override
	public long getLongValue() {
		return value;
	}
}
