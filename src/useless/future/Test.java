package useless.future;

import java.util.Stack;

import useless.future.statements.BinaryOperatorStatement;
import useless.future.statements.Statement;
import useless.future.tokens.BinaryOperatorToken;
import useless.future.tokens.ConsumedToken;
import useless.future.tokens.SimpleStatementToken;
import useless.future.tokens.StatementToken;
import useless.future.tokens.VariableToken;

public class Test {
	private static final String INPUT = "print 1 1 + 1";

	public static void main(String[] args) {
		new Parser().addVariable(new VariableToken(1) {
			@Override
			public ConsumedToken<Variable> consume(String input, int index) {
				if(input.charAt(index) == '1') {
					return new ConsumedToken<Variable>(1, new Variable() {
						@Override
						public int getIntValue() {
							return 1;
						}
					});
				}
				return null;
			}
		}).addStatement(new BinaryOperatorToken("+", 4) {
			@Override
			public Statement getStatement() {
				return new BinaryOperatorStatement() {
					@Override
					protected Variable getResult(final Variable left, final Variable right) {
						return new Variable() {
							@Override
							public int getIntValue() {
								return left.getIntValue() + right.getIntValue();
							}
						};
					}
				};
			}

		}).addStatement(new StatementToken(1) {
			@Override
			public ConsumedToken<Statement> consume(String input, int index) {
				if(input.charAt(index) == ' ') {
					return new ConsumedToken<Statement>(1, null);
				}
				return null;
			}
		}).addStatement(new SimpleStatementToken("print", 2) {
			@Override
			public Statement getStatement() {
				return new Statement() {
					private Variable variable;

					@Override
					public void run() {
						System.out.println(variable.getIntValue());
					}
					
					@Override
					public void statementAdded(Statement statement, Stack<Variable> variables, Stack<Statement> operators) {
						this.variable = variables.pop();
					}
				};
			}
		}).parse(INPUT).run();
	}
}
