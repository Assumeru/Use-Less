package useless.future.tokens;

public class ConsumedToken<T> {
	private int length;
	private T token;

	public ConsumedToken(int length, T token) {
		this.length = length;
		this.token = token;
	}

	public int getLength() {
		return length;
	}

	public T getToken() {
		return token;
	}
}
