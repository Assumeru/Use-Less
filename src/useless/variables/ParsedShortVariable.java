package useless.variables;


public class ParsedShortVariable extends ParsedLongVariable {
	private static final long serialVersionUID = -4708155802065632520L;

	public ParsedShortVariable(short value) {
		super(value);
	}

	@Override
	public byte[] getValue() {
		return new byte[] {
			(byte) (getLongValue() & 0xFF),
			(byte) ((getLongValue() >> 8) & 0xFF)
		};
	}
}
