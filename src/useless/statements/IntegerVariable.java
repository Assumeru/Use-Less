package useless.statements;

public class IntegerVariable extends ConstantVariable {
	private static final long serialVersionUID = 8976364581560369023L;

	public IntegerVariable(int value) {
		super(toBytes(value));
	}

	public static byte[] toBytes(int value) {
		return new byte[] {
			(byte) (value & 0xFF),
			(byte) ((value >> 8) & 0xFF),
			(byte) ((value >> 16) & 0xFF),
			(byte) ((value >> 24) & 0xFF)
		};
	}

	public static int fromBytes(byte[] value) {
		int val = 0;
		for(int i = Math.min(value.length - 1, 3); i >= 0; i--) {
			val = (val << 8) | (value[i] & 0xFF);
		}
		return val;
	}
}
