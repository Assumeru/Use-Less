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
			String line = doPreParse(in.nextLine());
			if(line != null) {
				try {
					List<Statement> statements = parseLine(line);
					parsedStatements.addAll(statements);
					lineStatements.addAll(statements);
				} catch (ParseException e) {
					throw new ParseException(e, e.getToken(), lineNumber);
				}
			}
			doPostParse(line);
			lineNumber++;
		}
		in.close();
		return new Program(parsedStatements);
	}

	private String doPreParse(String line) {
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

	private void doPostParse(String line) {
		for(Iterator<Statement> it = parsedStatements.iterator(); it.hasNext();) {
			Statement statement = it.next();
			if(statement instanceof AfterParseStatement) {
				LineStatementResult result = ((AfterParseStatement)statement).onAfterParse(this, line);
				line = result.getLine();
				if(result.deleteStatement()) {
					it.remove();
				}
			}
		}
	}

	private List<Statement> parseLine(String nextLine) throws ParseException {
		List<Statement> statements = new ArrayList<Statement>();
		String[] tokens = nextLine.split("\\s");
		for(int i = 0; i < tokens.length; i++) {
			for(TokenParser parser : tokenParsers) {
				if(parser.matches(this, tokens[i])) {
					ParseResult result = parser.parse(this, i, tokens);
					Statement statement = result.getStatement();
					if(statement != null) {
						statements.add(statement);
					}
					i += result.getExtraTokensConsumed();
					break;
				}
			}
		}
		return statements;
	}

	public IO getIO() {
		return io;
	}

	public LinkedList<Statement> getLineStatements() {
		return lineStatements;
	}
}
