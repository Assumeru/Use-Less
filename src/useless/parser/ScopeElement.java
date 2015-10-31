package useless.parser;

import java.util.ArrayList;
import java.util.List;

public class ScopeElement {
	private List<ParsedItem> parsedTokens;
	private Scope scope;

	public ScopeElement(Scope scope) {
		this.scope = scope;
		parsedTokens = new ArrayList<ParsedItem>();
	}

	public String getEnd() {
		return scope.getEnd();
	}

	public List<ParsedItem> getParsedTokens() {
		return parsedTokens;
	}

	public ParsedItem getAfter() {
		return scope.getAfter();
	}

	public ParsedItem getBefore() {
		return scope.getBefore();
	}

	public boolean consumesToken() {
		return scope.consumesToken();
	}
}