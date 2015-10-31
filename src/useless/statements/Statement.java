package useless.statements;

import useless.parser.ParsedItem;
import useless.program.Program;

public abstract class Statement implements ParsedItem {
	private static final long serialVersionUID = -2986916977140738376L;

	public abstract void run(Program program);
}
