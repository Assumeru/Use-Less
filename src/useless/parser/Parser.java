package useless.parser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import useless.data.IO;
import useless.exceptions.ParseException;
import useless.program.Program;
import useless.statements.Statement;
import useless.tokens.ParsedToken;
import useless.tokens.Token;

public class Parser {
	private static final int BUFFER_SIZE = 1024;
	private List<Token> tokens;
	private List<Scope> scopes;
	private Stack<ScopeElement> tokenScopeStack;
	private IO io;

	public Parser(IO io, List<Token> list) {
		this.io = io;
		tokens = list;
		scopes = new ArrayList<>();
	}

	public Parser addToken(Token token) {
		tokens.add(token);
		return this;
	}

	public Parser addScope(Scope scope) {
		scopes.add(scope);
		return this;
	}

	public Program parse() throws ParseException, IOException {
		return parse("UTF-8");
	}

	public Program parse(String encoding) throws ParseException, IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[BUFFER_SIZE];
		int read;
		while((read = io.in.read(buffer)) != -1) {
			out.write(buffer, 0, read);
		}
		return parseInternal(out.toString(encoding));
	}

	private Program parseInternal(String input) throws ParseException {
		ScopeElement base = new ScopeElement(null);
		tokenScopeStack = new Stack<ScopeElement>();
		tokenScopeStack.add(base);
		int index = 0;
		while(index < input.length()) {
			ScopeTokens scope = getScope(input, index);
			List<ParsedItem> parsedTokens = scope.getParsedTokens();
			index += scope.getConsumedCharacters();
			if(scope.getConsumedCharacters() > 0) {
				continue;
			}
			ConsumedToken result = parse(input, index);
			if(result != null) {
				if(result.getToken() != null) {
					parsedTokens.add(result.getToken());
				}
				index += result.getLength();
				continue;
			} else {
				throw new ParseException("Failed to parse " + input.substring(index), index);
			}
		}
		if(tokenScopeStack.size() > 1) {
			throw new ParseException("Failed to close scope, missing: "+tokenScopeStack.peek().getEnd(), -1);
		}
		return createProgram(parseTokens(tokenScopeStack.pop().getParsedTokens()));
	}

	private ScopeTokens getScope(String input, int index) throws ParseException {
		int consumedCharacters = 0;
		if(tokenScopeStack.size() > 1) {
			ScopeElement current = tokenScopeStack.peek();
			if(input.startsWith(current.getEnd(), index)) {
				tokenScopeStack.pop();
				List<ParsedItem> list = tokenScopeStack.peek().getParsedTokens();
				if(current.getBefore() != null) {
					list.add(current.getBefore());
				}
				list.addAll(parseTokens(current.getParsedTokens()));
				if(current.getAfter() != null) {
					list.add(current.getAfter());
				}
				if(current.consumesToken()) {
					consumedCharacters += current.getEnd().length();
				}
			}
		}
		for(Scope scope : scopes) {
			if(input.startsWith(scope.getStart(), index)) {
				ScopeElement next = new ScopeElement(scope);
				tokenScopeStack.add(next);
				if(scope.consumesToken()) {
					consumedCharacters += scope.getStart().length();
				}
				return new ScopeTokens(next.getParsedTokens(), consumedCharacters);
			}
		}
		return new ScopeTokens(tokenScopeStack.peek().getParsedTokens(), consumedCharacters);
	}

	private List<ParsedItem> parseTokens(List<ParsedItem> parsedTokens) throws ParseException {
		int level = Integer.MIN_VALUE;
		for(ParsedItem item : parsedTokens) {
			if(item instanceof ParsedToken) {
				level = Math.max(((ParsedToken) item).getPrecedence(), level);
			}
		}
		while(true) {
			boolean foundToken = false;
			int nextLevel = Integer.MIN_VALUE;
			for(int i = 0; i < parsedTokens.size(); i++) {
				if(parsedTokens.get(i) instanceof ParsedToken) {
					foundToken = true;
					ParsedToken token = (ParsedToken) parsedTokens.get(i);
					if(token.getPrecedence() >= level) {
						boolean reset = token.parseStatement(parsedTokens, i);
						if(reset) {
							i = -1;
						}
						continue;
					} else {
						nextLevel = Math.max(token.getPrecedence(), nextLevel);
					}
				}
			}
			if(foundToken) {
				level = nextLevel;
			} else {
				break;
			}
		}
		return parsedTokens;
	}

	private Program createProgram(List<ParsedItem> parsedTokens) {
		List<Statement> statements = new ArrayList<>();
		for(ParsedItem item : parsedTokens) {
			if(item instanceof Statement) {
				statements.add((Statement) item);
			}
		}
		return new Program(statements);
	}

	private ConsumedToken parse(String input, int index) {
		for(Token token : tokens) {
			ConsumedToken result = token.consume(input, index);
			if(result != null) {
				return result;
			}
		}
		return null;
	}
}
