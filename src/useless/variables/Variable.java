package useless.variables;

public interface Variable {
	public byte[] getValue();

	public Variable multiply(Variable value);

	public Variable add(Variable value);

	public Variable subtract(Variable value);

	public Variable divide(Variable value);

	public static short getShortValue(Variable variable) {
		short value = 0;
		byte[] bytes = variable.getValue();
		if(bytes.length > 2) {
			throw new NumberFormatException("variable is not a short");
		}
		for(int i = bytes.length - 1; i >= 0; i--) {
			value = (short) ((value << 8) | (bytes[i] & 0xFF));
		}
		return value;
	}

	public static int getIntValue(Variable variable) {
		int value = 0;
		byte[] bytes = variable.getValue();
		if(bytes.length > 4) {
			throw new NumberFormatException("variable is not an int");
		}
		for(int i = bytes.length - 1; i >= 0; i--) {
			value = (value << 8) | (bytes[i] & 0xFF);
		}
		return value;
	}

	public static long getLongValue(Variable variable) {
		long value = 0;
		byte[] bytes = variable.getValue();
		if(bytes.length > 8) {
			throw new NumberFormatException("variable is not a long");
		}
		for(int i = bytes.length - 1; i >= 0; i--) {
			value = (value << 8) | (bytes[i] & 0xFF);
		}
		return value;
	}
}
