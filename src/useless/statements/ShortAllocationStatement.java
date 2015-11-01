package useless.statements;

import useless.tokens.NameToken.Name;

public class ShortAllocationStatement extends AllocationStatement {
	private static final long serialVersionUID = 78135533482290528L;

	public ShortAllocationStatement(Name name) {
		super(name, 2);
	}
}
