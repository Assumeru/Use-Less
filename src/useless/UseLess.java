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
import useless.parser.Scope;
import useless.program.Program;
import useless.tokens.AdditionToken;
import useless.tokens.AssignmentToken;
import useless.tokens.DivisionToken;
import useless.tokens.IntegerAllocationToken;
import useless.tokens.IntegerToken;
import useless.tokens.MultiplicationToken;
import useless.tokens.NameToken;
import useless.tokens.NamespaceToken;
import useless.tokens.PrintMemoryToken;
import useless.tokens.PrintToken;
import useless.tokens.StringToken;
import useless.tokens.SubtractionToken;
import useless.tokens.Token;
import useless.tokens.WhiteSpaceToken;

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
			program = parseProgram(arguments.getString("encoding", "UTF-8"));
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

	private static Program parseProgram(String encoding) {
		try {
			return new Parser(io, getTokenParsers()).addScope(new Scope("(", ")")).parse(encoding);
		} catch (ParseException | IOException e) {
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
		System.exit(1);
	}

	private static Program restoreProgram() {
		try(ObjectInputStream input = new ObjectInputStream(io.in)) {
			return (Program) input.readObject();
		} catch (IOException | ClassCastException | ClassNotFoundException e) {
			exit("Provided file was not a runnable program", e);
		}
		return null;
	}

	private static List<Token> getTokenParsers() {
		List<Token> tokenParsers = new ArrayList<>();
		tokenParsers.add(new StringToken());
		tokenParsers.add(new IntegerToken());
		tokenParsers.add(new NamespaceToken());
		tokenParsers.add(new IntegerAllocationToken());
		tokenParsers.add(new MultiplicationToken());
		tokenParsers.add(new DivisionToken());
		tokenParsers.add(new AdditionToken());
		tokenParsers.add(new SubtractionToken());
		tokenParsers.add(new AssignmentToken());
		tokenParsers.add(new PrintMemoryToken());
		tokenParsers.add(new PrintToken());
		tokenParsers.add(new WhiteSpaceToken());
		tokenParsers.add(new NameToken());
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
