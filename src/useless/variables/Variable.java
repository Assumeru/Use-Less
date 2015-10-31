package useless.variables;

public interface Variable {
	public byte[] getValue();

	public Variable multiply(Variable value);

	public Variable add(Variable value);

	public Variable subtract(Variable value);

	public Variable divide(Variable value);
}
