package useless.variables;

public class ParsedLongVariable extends AbstractIntegerVariable {
	private static final long serialVersionUID = 5617765803776010959L;
	private long value;

	public ParsedLongVariable(long value) {
		this.value = value;
	}

	@Override
	public byte[] getValue() {
		return new byte[] {
			(byte) (value & 0xFF),
			(byte) ((value >> 8) & 0xFF),
			(byte) ((value >> 16) & 0xFF),
			(byte) ((value >> 24) & 0xFF),
			(byte) ((value >> 32) & 0xFF),
			(byte) ((value >> 40) & 0xFF),
			(byte) ((value >> 48) & 0xFF),
			(byte) ((value >> 56) & 0xFF)
		};
	}

	public long getLongValue() {
		return value;
	}
}
