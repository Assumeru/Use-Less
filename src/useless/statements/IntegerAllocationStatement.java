package useless.statements;

import useless.tokens.NameToken.Name;

public class IntegerAllocationStatement extends AllocationStatement {
	private static final long serialVersionUID = 7333192204119065069L;

	public IntegerAllocationStatement(Name name) {
		super(name, 4);
	}
}
