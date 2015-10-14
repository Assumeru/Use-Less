package useless.future.statements;

import java.util.Stack;

import useless.future.Variable;

public interface Statement extends Runnable {
	public abstract void statementAdded(Statement statement, Stack<Variable> variables, Stack<Statement> operators);
}
