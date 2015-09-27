package useless.statements;

import java.math.BigInteger;

public class LongVariable extends ConstantVariable {
	private static final long serialVersionUID = 6864715353736489194L;

	public LongVariable(long value) {
		super(switchEndianess(BigInteger.valueOf(value).toByteArray()));
	}

	private static byte[] switchEndianess(byte[] little) {
		byte[] var = new byte[8];
		int n = 0;
		for(int i = little.length - 1; i >= 0; i--, n++) {
			var[n] = little[i];
		}
		for(; n < var.length; n++) {
			var[n] = 0;
		}
		return var;
	}
}
