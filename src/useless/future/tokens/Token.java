package useless.future.tokens;

public abstract class Token<E extends Token<?, ?>, S> implements Comparable<E> {
	private int precedence;

	public Token(int precedence) {
		this.precedence = precedence;
	}

	public int getPrecedence() {
		return precedence;
	}

	public abstract ConsumedToken<S> consume(String input, int index);

	@Override
	public int compareTo(E o) {
		return o.getPrecedence() - getPrecedence();
	}
}
