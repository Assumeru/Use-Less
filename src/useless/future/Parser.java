package useless.future;

import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;

import useless.future.statements.Statement;
import useless.future.tokens.ConsumedToken;
import useless.future.tokens.StatementToken;
import useless.future.tokens.Token;
import useless.future.tokens.VariableToken;

public class Parser {
	private Stack<Statement> parsedStatements;
	private Stack<Variable> parsedVariables;
	private Set<StatementToken> statements;
	private Set<VariableToken> variables;

	public Parser() {
		statements = new TreeSet<>();
		variables = new TreeSet<>();
		parsedStatements = new Stack<>();
		parsedVariables = new Stack<>();
	}

	public Parser addStatement(StatementToken statement) {
		statements.add(statement);
		return this;
	}

	public Parser addVariable(VariableToken variable) {
		variables.add(variable);
		return this;
	}

	public Runnable parse(String input) {
		int index = 0;
		while(index < input.length()) {
			ConsumedToken<Variable> varResult = parse(variables, input, index);
			if(varResult != null) {
				if(varResult.getToken() != null) {
					parsedVariables.add(varResult.getToken());
				}
				index += varResult.getLength();
				continue;
			}
			ConsumedToken<Statement> staResult = parse(statements, input, index);
			if(staResult != null) {
				if(staResult.getToken() != null) {
					notifyAdded(staResult.getToken());
					parsedStatements.add(staResult.getToken());
				}
				index += staResult.getLength();
				continue;
			}
		}
		notifyAdded(null);
		return new Runnable() {
			@Override
			public void run() {
				for(Statement statement : parsedStatements) {
					statement.run();
				}
			}
		};
	}

	private void notifyAdded(Statement statement) {
		if(!parsedStatements.isEmpty()) {
			parsedStatements.peek().statementAdded(statement, parsedVariables, parsedStatements);
		}
	}

	private <E extends Token<?, S>, S> ConsumedToken<S> parse(Set<E> tokens, String input, int index) {
		for(E token : tokens) {
			ConsumedToken<S> result = token.consume(input, index);
			if(result != null) {
				return result;
			}
		}
		return null;
	}
}