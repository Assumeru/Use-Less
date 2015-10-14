package useless.data;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class TransactionStream extends InputStream {
	private static final int MIN_BUFFER_SIZE = 8;
	private InputStream input;
	private int[] buffer;
	private int size;
	private int index;
	private int previous;

	public TransactionStream(InputStream input) {
		this.input = input;
		buffer = new int[MIN_BUFFER_SIZE];
	}

	public void commit() {
		int length = buffer.length - index;
		int[] replacement = new int[Math.max(length, MIN_BUFFER_SIZE)];
		System.arraycopy(buffer, index, replacement, 0, length);
		buffer = replacement;
		size -= index;
		index = previous = 0;
	}

	public void rollback() {
		index = previous;
	}

	@Override
	public int read() throws IOException {
		if(index < size) {
			return buffer[index++];
		} else if(index >= buffer.length) {
			int length = buffer.length * 2;
			if(length < 1) {
				length = Integer.MAX_VALUE;
			}
			buffer = Arrays.copyOf(buffer, length);
		}
		buffer[index] = input.read();
		size++;
		return buffer[index++];
	}

	@Override
	public void close() throws IOException {
		input.close();
	}
}
