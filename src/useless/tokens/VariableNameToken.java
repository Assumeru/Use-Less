package useless.tokens;

import useless.exceptions.ParseException;
import useless.parser.ParsedItem;
import useless.program.Program;
import useless.statements.Statement;
import useless.tokens.NameToken.Name;
import useless.variables.NamedVariable;
import useless.variables.ParsedVariable;

public class VariableNameToken extends PrefixToken {
	public VariableNameToken() {
		super("_", new ParsedVariableNameToken());
	}

	public static class ParsedVariableNameToken extends ParsedPrefixToken {
		private static final long serialVersionUID = -5750518379484250407L;

		public ParsedVariableNameToken() {
			super(OPERATION_PRE_CONSTRUCT);
		}

		@Override
		protected ParsedItem getResult(ParsedItem variable) throws ParseException {
			if(variable instanceof ParsedVariable) {
				return new Name(variable.toString());
			} else if(variable instanceof Name) {
				return new VariableName((Name) variable);
			}
			return null;
		}
	}

	public static class VariableName extends Name implements Statement {
		private static final long serialVersionUID = 6517047370991300323L;
		private NamedVariable name;

		public VariableName(Name name) {
			super(null);
			this.name = new NamedVariable(name);
		}

		@Override
		public String getName() {
			return name.toString();
		}

		@Override
		public void run(Program program) {
			name.run(program);
		}
	}
}
