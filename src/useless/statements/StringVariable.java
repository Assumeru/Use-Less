package useless.statements;

public class StringVariable extends ConstantVariable {
	private static final long serialVersionUID = -4656123689566860334L;

	public StringVariable(String value) {
		super(value.getBytes());
	}
}
