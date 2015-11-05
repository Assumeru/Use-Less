package useless.variables;

import useless.tokens.IntegerToken;

public abstract class AbstractIntegerVariable extends ParsedVariable {
	private static final long serialVersionUID = 9148918535828491830L;

	public Variable multiply(Variable value) {
		if(value instanceof AbstractIntegerVariable) {
			return IntegerToken.createVariable(getLongValue() * ((AbstractIntegerVariable) value).getLongValue());
		}
		return super.multiply(value);
	}

	public Variable add(Variable value) {
		if(value instanceof AbstractIntegerVariable) {
			return IntegerToken.createVariable(getLongValue() + ((AbstractIntegerVariable) value).getLongValue());
		} else if(value instanceof ParsedStringVariable) {
			return new ParsedStringVariable(toString() + value.toString());
		}
		return super.add(value);
	}

	public Variable subtract(Variable value) {
		if(value instanceof AbstractIntegerVariable) {
			return IntegerToken.createVariable(getLongValue() - ((AbstractIntegerVariable) value).getLongValue());
		}
		return super.subtract(value);
	}

	public Variable divide(Variable value) {
		if(value instanceof AbstractIntegerVariable) {
			return IntegerToken.createVariable(getLongValue() / ((AbstractIntegerVariable) value).getLongValue());
		}
		return super.divide(value);
	}

	public String toString() {
		return String.valueOf(getLongValue());
	}

	public abstract long getLongValue();
}
