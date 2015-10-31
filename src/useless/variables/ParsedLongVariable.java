package useless.variables;

import useless.tokens.IntegerToken;


public class ParsedLongVariable implements ParsedVariable {
	private static final long serialVersionUID = 5617765803776010959L;
	private long value;

	public ParsedLongVariable(long value) {
		this.value = value;
	}

	@Override
	public byte[] getValue() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setValue(Variable value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable multiply(Variable value) {
		if(value instanceof ParsedLongVariable) {
			return IntegerToken.createVariable(getLongValue() * ((ParsedLongVariable) value).getLongValue());
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable add(Variable value) {
		if(value instanceof ParsedLongVariable) {
			return IntegerToken.createVariable(getLongValue() + ((ParsedLongVariable) value).getLongValue());
		} else if(value instanceof ParsedStringVariable) {
			return new ParsedStringVariable(this.value + value.toString());
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable subtract(Variable value) {
		if(value instanceof ParsedLongVariable) {
			return IntegerToken.createVariable(getLongValue() - ((ParsedLongVariable) value).getLongValue());
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable divide(Variable value) {
		if(value instanceof ParsedLongVariable) {
			return IntegerToken.createVariable(getLongValue() / ((ParsedLongVariable) value).getLongValue());
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return String.valueOf(value);
	}

	public long getLongValue() {
		return value;
	}
}
