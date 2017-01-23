import java.util.HashMap;

public class AlphabetTable {
	private HashMap<Character, TurtleCommands> alphabetSymbols;

	public HashMap<Character, TurtleCommands> getAlphabetSymbols() {
		return alphabetSymbols;
	}

	public void setAlphabetSymbols(HashMap<Character, TurtleCommands> alphabetSymbol) {
		this.alphabetSymbols = alphabetSymbol;
	}
	
	public boolean hasKey(Character c) {
		return alphabetSymbols.containsKey(c);
	}
	
	public TurtleCommands get(Character c) {
		return alphabetSymbols.get(c);
	}
}
