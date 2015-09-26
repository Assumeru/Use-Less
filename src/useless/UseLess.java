package useless;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import useless.data.IO;
import useless.data.Memory;
import useless.exceptions.ParseException;
import useless.parser.Parser;
import useless.parser.TokenParser;
import useless.program.Program;
import useless.tokens.Allocate;
import useless.tokens.Deallocate;
import useless.tokens.PrintMemory;
import useless.tokens.PrintToken;
import useless.tokens.SkipNextLine;

public class UseLess {
	private static final String HELP = "Usage: ???";
	private static IO io = new IO(System.in, System.out, System.err);

	public static void main(String[] args) {
		Arguments arguments = new Arguments(args);
		if(arguments.contains("help")) {
			exit(HELP);
		}
		boolean fromFile = arguments.contains("from-file");
		Program program;
		if(fromFile) {
			program = restoreProgram();
		} else {
			program = parseProgram();
		}
		if(!fromFile || !arguments.contains("restore-memory")) {
			program.setMemory(new Memory(arguments.getInt("memory", 10240)));
		}
		program.setIO(io);
		String output = arguments.getString("output", null);
		if(output != null) {
			saveProgram(output, program);
		}
		if(arguments.contains("run")) {
			program.run();
		}
	}

	private static Program parseProgram() {
		try {
			return new Parser(io, getTokenParsers()).parse();
		} catch (ParseException e) {
			exit(e.getMessage(), e);
			return null;
		}
	}

	private static void saveProgram(String output, Program program) {
		File file = new File(output);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch(IOException | SecurityException e) {
				exit("Could not save program: failed to create file "+file, e);
			}
		}
		if(!file.canWrite()) {
			exit("Could not save program: file is not writable "+file);
		}
		try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
			out.writeObject(program);
		} catch (IOException e) {
			exit("Failed to save program", e);
		}
		io.out.println("Saved program to "+file);
	}

	private static void exit(String message) {
		io.out.println(message);
		System.exit(0);
	}

	private static void exit(String message, Throwable throwable) {
		io.out.println(message);
		throwable.printStackTrace(io.err);
		System.exit(message.hashCode());
	}

	private static Program restoreProgram() {
		try(ObjectInputStream input = new ObjectInputStream(io.in)) {
			return (Program) input.readObject();
		} catch (IOException | ClassCastException | ClassNotFoundException e) {
			exit("Provided file was not a runnable program", e);
		}
		return null;
	}

	private static List<TokenParser> getTokenParsers() {
		List<TokenParser> tokenParsers = new ArrayList<TokenParser>();
		tokenParsers.add(new SkipNextLine());
		tokenParsers.add(new Allocate());
		tokenParsers.add(new Deallocate());
		tokenParsers.add(new PrintMemory());
		tokenParsers.add(new PrintToken());
		return tokenParsers;
	}

	private static class Arguments {
		private String[] args;

		public Arguments(String[] args) {
			this.args = args;
		}

		public boolean contains(String key) {
			String match = "--" + key;
			for(int i = 0; i < args.length; i++) {
				if(args[i].equals(match)) {
					return true;
				}
			}
			return false;
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
