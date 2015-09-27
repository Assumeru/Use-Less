package useless.parser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import useless.data.IO;
import useless.exceptions.ParseException;
import useless.program.Program;

public class Parser {
	private IO io;
	private List<TokenParser> tokenParsers;
	private Scanner in;
	private List<Statement> parsedStatements;
	private LinkedList<Statement> lineStatements;

	public Parser(IO io, List<TokenParser> tokenParsers) {
		this.io = io;
		this.tokenParsers = tokenParsers;
	}

	public Program parse() throws ParseException {
		parsedStatements = new ArrayList<Statement>();
		in = new Scanner(io.in);
		int lineNumber = 1;
		while(in.hasNextLine()) {
			lineStatements = new LinkedList<Statement>();
			try {
				String line = doPreParse(in.nextLine());
				if(line != null) {
					List<Statement> statements = parseLine(line);
					parsedStatements.addAll(statements);
				}
				doPostParse(line);
			} catch (ParseException e) {
				throw new ParseException(e, e.getToken(), lineNumber);
			}
			lineNumber++;
		}
		in.close();
		return new Program(parsedStatements);
	}

	private String doPreParse(String line) throws ParseException {
		line = line.trim();
		if(line.isEmpty()) {
			return null;
		}
		for(Iterator<Statement> it = parsedStatements.iterator(); it.hasNext();) {
			Statement statement = it.next();
			if(statement instanceof BeforeParseStatement) {
				LineStatementResult result = ((BeforeParseStatement)statement).onBeforeParse(this, line);
				line = result.getLine();
				if(result.deleteStatement()) {
					it.remove();
				}
			}
		}
		return line;
	}

	private void doPostParse(String line) throws ParseException {
		for(int i = 0; i < parsedStatements.size(); i++) {
			Statement statement = parsedStatements.get(i);
			if(statement instanceof AfterParseStatement) {
				LineStatementResult result = ((AfterParseStatement)statement).onAfterParse(this, line, i);
				line = result.getLine();
				if(result.deleteStatement()) {
					parsedStatements.remove(i);
					i--;
				}
			}
		}
	}

	private int parseToken(String[] tokens, int i, List<Statement> statements) throws ParseException {
		for(TokenParser parser : tokenParsers) {
			if(parser.matches(this, tokens[i])) {
				ParseResult result = parser.parse(this, i, tokens);
				Statement statement = result.getStatement();
				if(statement != null) {
					statements.add(statement);
					lineStatements.add(statement);
				}
				return result.getExtraTokensConsumed();
			}
		}
		return 0;
	}

	private List<Statement> parseLine(String nextLine) throws ParseException {
		List<Statement> statements = new ArrayList<Statement>();
		String[] tokens = nextLine.split("\\s");
		for(int i = 0; i < tokens.length; i++) {
			i += parseToken(tokens, i, statements);
		}
		return statements;
	}

	public IO getIO() {
		return io;
	}

	public LinkedList<Statement> getLineStatements() {
		return lineStatements;
	}

	public List<Statement> getParsedStatements() {
		return parsedStatements;
	}
}
