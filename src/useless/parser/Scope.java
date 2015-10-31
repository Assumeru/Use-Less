package useless.parser;


public class Scope {
	private String start;
	private String end;
	private boolean consumeToken;
	private ParsedItem after;
	private ParsedItem before;

	public Scope(String start, String end) {
		this(start, end, true);
	}

	public Scope(String start, String end, boolean consumeToken) {
		this(start, end, consumeToken, null, null);
	}

	public Scope(String start, String end, boolean consumeToken, ParsedItem after, ParsedItem before) {
		this.start = start;
		this.end = end;
		if(start.isEmpty() || end.isEmpty()) {
			throw new IllegalArgumentException("Scope designators cannot be empty");
		}
		this.consumeToken = consumeToken;
		this.after = after;
		this.before = before;
	}

	public String getStart() {
		return start;
	}

	public String getEnd() {
		return end;
	}

	public boolean consumesToken() {
		return consumeToken;
	}

	public ParsedItem getAfter() {
		return after;
	}

	public ParsedItem getBefore() {
		return before;
	}
}
