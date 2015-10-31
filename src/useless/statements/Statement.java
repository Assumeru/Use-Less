package useless.statements;

import useless.parser.ParsedItem;
import useless.program.Program;

public interface Statement extends ParsedItem {
	public void run(Program program);
}
