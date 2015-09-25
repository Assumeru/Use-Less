package useless.data;

import java.io.InputStream;
import java.io.PrintStream;

public class IO {
	public final InputStream in;
	public final PrintStream out;
	public final PrintStream err;

	public IO(InputStream in, PrintStream out, PrintStream err) {
		this.in = in;
		this.out = out;
		this.err = err;
	}
}
