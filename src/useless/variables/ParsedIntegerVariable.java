package useless.variables;

public class ParsedIntegerVariable extends ParsedLongVariable {
	private static final long serialVersionUID = -5364334210263703650L;

	public ParsedIntegerVariable(int value) {
		super(value);
	}

	@Override
	public byte[] getValue() {
		return new byte[] {
			(byte) (getLongValue() & 0xFF),
			(byte) ((getLongValue() >> 8) & 0xFF),
			(byte) ((getLongValue() >> 16) & 0xFF),
			(byte) ((getLongValue() >> 24) & 0xFF)
		};
	}
}
