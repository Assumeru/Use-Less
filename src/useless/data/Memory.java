package useless.data;

import java.io.Serializable;

public class Memory implements Serializable {
	private static final long serialVersionUID = 3681936649605382657L;
	private byte[] data;
	private int maxSize;

	public Memory(int maxSize) {
		this.maxSize = maxSize;
		data = new byte[0];
	}

	public int allocate(int size) {
		int newSize = this.data.length + size;
		if(newSize > maxSize) {
			throw new OutOfMemoryError("failed to allocate " + newSize + " bytes");
		}
		byte[] data = new byte[newSize];
		System.arraycopy(this.data, 0, data, 0, this.data.length);
		int pos = this.data.length;
		this.data = data;
		return pos;
	}

	public byte[] get(int pos, int length) {
		if(pos < 0) {
			pos = data.length + pos;
		}
		if(pos < 0) {
			throw new IllegalArgumentException("pos < 0");
		} else if(length < 1) {
			throw new IllegalArgumentException("length < 1");
		} else if(pos < data.length && pos + length <= data.length) {
			byte[] dest = new byte[length];
			System.arraycopy(data, pos, dest, 0, length);
			return dest;
		}
		throw new StackOverflowError();
	}

	public void put(int pos, byte[] data) {
		if(pos < 0) {
			pos = data.length + pos;
		}
		if(pos < 0) {
			throw new IllegalArgumentException("pos < 0");
		} else if(pos + data.length > this.data.length) {
			throw new StackOverflowError();
		}
		System.arraycopy(data, 0, this.data, pos, data.length);
	}

	public void free(int pos, int length) {
		if(pos < 0) {
			pos = data.length + pos;
		}
		if(pos < 0) {
			throw new IllegalArgumentException("pos < 0");
		} else if(length < 1) {
			throw new IllegalArgumentException("length < 1");
		}
		int size = Math.max(this.data.length - length, 0);
		byte[] data = new byte[size];
		System.arraycopy(this.data, 0, data, 0, pos);
		System.arraycopy(this.data, pos + length, data, pos, size - pos);
		this.data = data;
	}

	public int size() {
		return data.length;
	}
}
