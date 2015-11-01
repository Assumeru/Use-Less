package useless.variables;

import useless.tokens.IntegerToken;

public abstract class AbstractIntegerVariable implements ParsedVariable {
	private static final long serialVersionUID = 9148918535828491830L;

	@Override
	public Variable multiply(Variable value) {
		if(value instanceof AbstractIntegerVariable) {
			return IntegerToken.createVariable(getLongValue() * ((AbstractIntegerVariable) value).getLongValue());
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable add(Variable value) {
		if(value instanceof AbstractIntegerVariable) {
			return IntegerToken.createVariable(getLongValue() + ((AbstractIntegerVariable) value).getLongValue());
		} else if(value instanceof ParsedStringVariable) {
			return new ParsedStringVariable(toString() + value.toString());
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable subtract(Variable value) {
		if(value instanceof AbstractIntegerVariable) {
			return IntegerToken.createVariable(getLongValue() - ((AbstractIntegerVariable) value).getLongValue());
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable divide(Variable value) {
		if(value instanceof AbstractIntegerVariable) {
			return IntegerToken.createVariable(getLongValue() / ((AbstractIntegerVariable) value).getLongValue());
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return String.valueOf(getLongValue());
	}

	public abstract long getLongValue();

	public static short getShortValue(Variable variable) {
		short value = 0;
		byte[] bytes = variable.getValue();
		if(bytes.length > 2) {
			throw new NumberFormatException("variable is not a short");
		}
		for(byte b : bytes) {
			value = (short) ((value << 8) | (b & 0xFF));
		}
		return value;
	}

	public static int getIntValue(Variable variable) {
		int value = 0;
		byte[] bytes = variable.getValue();
		if(bytes.length > 4) {
			throw new NumberFormatException("variable is not an int");
		}
		for(byte b : bytes) {
			value = (value << 8) | (b & 0xFF);
		}
		return value;
	}

	public static long getLongValue(Variable variable) {
		long value = 0;
		byte[] bytes = variable.getValue();
		if(bytes.length > 8) {
			throw new NumberFormatException("variable is not a long");
		}
		for(byte b : bytes) {
			value = (value << 8) | (b & 0xFF);
		}
		return value;
	}
}
