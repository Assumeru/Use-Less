package useless.statements;

import useless.tokens.NameToken.Name;

public class LongAllocationStatement extends AllocationStatement {
	private static final long serialVersionUID = -124251737573713742L;

	public LongAllocationStatement(Name name) {
		super(name, 8);
	}
}
