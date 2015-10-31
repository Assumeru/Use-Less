package useless.parser;

import java.util.List;

public class ScopeTokens {
	private List<ParsedItem> parsedTokens;
	private int consumedCharacters;

	public ScopeTokens(List<ParsedItem> parsedTokens, int consumedCharacters) {
		this.parsedTokens = parsedTokens;
		this.consumedCharacters = consumedCharacters;
	}

	public List<ParsedItem> getParsedTokens() {
		return parsedTokens;
	}

	public int getConsumedCharacters() {
		return consumedCharacters;
	}
}
