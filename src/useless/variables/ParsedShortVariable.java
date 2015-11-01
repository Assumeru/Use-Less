package useless.variables;

public class ParsedShortVariable extends AbstractIntegerVariable {
	private static final long serialVersionUID = -4708155802065632520L;
	private short value;

	public ParsedShortVariable(short value) {
		this.value = value;
	}

	@Override
	public byte[] getValue() {
		return new byte[] {
			(byte) (value & 0xFF),
			(byte) ((value >> 8) & 0xFF)
		};
	}

	public long getLongValue() {
		return value;
	}
}
