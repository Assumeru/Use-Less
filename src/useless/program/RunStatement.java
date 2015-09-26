package useless.program;

import java.io.Serializable;

import useless.parser.Statement;

public interface RunStatement extends Statement, Serializable {
	public void run(Program program);
}
