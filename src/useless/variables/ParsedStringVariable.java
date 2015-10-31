package useless.variables;

public class ParsedStringVariable implements ParsedVariable {
	private static final long serialVersionUID = 6239159129601142074L;
	private String value;

	public ParsedStringVariable(String value) {
		this.value = value;
	}

	@Override
	public byte[] getValue() {
		return value.getBytes();
	}

	@Override
	public void setValue(Variable value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable multiply(Variable value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable add(Variable value) {
		if(value instanceof ParsedVariable) {
			return new ParsedStringVariable(this.value + value.toString());
		}
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable subtract(Variable value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Variable divide(Variable value) {
		throw new UnsupportedOperationException();
	}

	@Override
	public String toString() {
		return value;
	}
}
