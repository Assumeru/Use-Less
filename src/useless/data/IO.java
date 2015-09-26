package useless.data;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

public class IO {
	public final InputStream in;
	public final PrintStream out;
	public final PrintStream err;

	public IO(InputStream in, PrintStream out, PrintStream err) {
		if(in == System.in) {
			in = new OpenInputStream(in);
		}
		if(out == System.out) {
			out = new OpenPrintStream(out);
		}
		if(err == System.err) {
			err = new OpenPrintStream(err);
		}
		this.in = in;
		this.out = out;
		this.err = err;
	}

	private static class OpenInputStream extends BufferedInputStream {
		public OpenInputStream(InputStream in) {
			super(in);
		}

		@Override
		public void close() throws IOException {
		}
	}

	private static class OpenPrintStream extends PrintStream {
		public OpenPrintStream(PrintStream out) {
			super(out, true);
		}

		@Override
		public void close() {
		}
	}
}
