package useless.variables;

import useless.parser.ParsedItem;
import useless.statements.AdditionStatement;
import useless.statements.DivisionStatement;
import useless.statements.MultiplicationStatement;
import useless.statements.SubtractionStatement;
import useless.tokens.IntegerToken;

public abstract class ParsedVariable implements ParsedItem, Variable {
	private static final long serialVersionUID = 202786191892913726L;

	@Override
	public Variable multiply(Variable value) {
		if(value.getValue().length <= 8 && getValue().length <= 8) {
			return IntegerToken.createVariable(Variable.getLongValue(value) * Variable.getLongValue(this));
		}
		return new MultiplicationStatement(this, value);
	}

	@Override
	public Variable add(Variable value) {
		if(value.getValue().length <= 8 && getValue().length <= 8) {
			return IntegerToken.createVariable(Variable.getLongValue(value) + Variable.getLongValue(this));
		}
		return new AdditionStatement(this, value);
	}

	@Override
	public Variable subtract(Variable value) {
		if(value.getValue().length <= 8 && getValue().length <= 8) {
			return IntegerToken.createVariable(Variable.getLongValue(value) - Variable.getLongValue(this));
		}
		return new SubtractionStatement(this, value);
	}

	@Override
	public Variable divide(Variable value) {
		if(value.getValue().length <= 8 && getValue().length <= 8) {
			return IntegerToken.createVariable(Variable.getLongValue(value) / Variable.getLongValue(this));
		}
		return new DivisionStatement(this, value);
	}
}
