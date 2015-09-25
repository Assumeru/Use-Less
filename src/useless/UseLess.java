package useless;

import useless.data.IO;
import useless.data.Memory;
import useless.parser.Parser;

public class UseLess {
	public static void main(String[] args) {
		Arguments arguments = new Arguments(args);
		new Parser(new IO(System.in, System.out, System.err), new Memory(arguments.getInt("memory", 10240)));
	}

	private static class Arguments {
		private String[] args;

		public Arguments(String[] args) {
			this.args = args;
		}

		public String getString(String key, String fallback) {
			String match = "--" + key;
			for(int i = 0; i < args.length; i++) {
				if(args[i].equals(match) && args.length >= i + 1) {
					return args[i + 1];
				}
			}
			return fallback;
		}

		public int getInt(String key, int fallback) {
			String value = getString(key, null);
			if(value != null) {
				try {
					return Integer.parseInt(value);
				} catch(NumberFormatException e) {}
			}
			return fallback;
		}
	}
}
