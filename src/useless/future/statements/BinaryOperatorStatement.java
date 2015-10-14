package useless.future.statements;

import java.util.Stack;

import useless.future.Variable;

public abstract class BinaryOperatorStatement implements Statement {
	protected abstract Variable getResult(Variable left, Variable right);

	@Override
	public void statementAdded(Statement newOperator, Stack<Variable> variables, Stack<Statement> operators) {
		if(variables.size() < 2) {
			throw new RuntimeException();
		}
		Variable right = variables.pop();
		Variable left = variables.pop();
		operators.pop();
		Variable result = getResult(left, right);
		if(result != null) {
			variables.add(result);
		}
	}

	@Override
	public void run() {}
}
