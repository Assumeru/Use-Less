package useless.variables;

public class ParsedStringVariable extends ParsedVariable {
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
	public Variable add(Variable value) {
		if(value instanceof ParsedVariable) {
			return new ParsedStringVariable(this.value + value.toString());
		}
		return super.add(value);
	}

	@Override
	public String toString() {
		return value;
	}
}
