import java.util.HashMap;

@SuppressWarnings("unused")
public class LSystem {
	private Character[] alphabet;
	private String axiom;
	private HashMap<Character, String> rules;
	
	public LSystem (Character[] alphabet, String axiom, HashMap<Character, String> rules){
		this.alphabet = alphabet;
		this.axiom = axiom;
		this.rules = rules;
	}
	
	public HashMap<Character, String> prepareHashMap(Character[] leftSide, String[] rightSide) throws Exception{
		if(leftSide.length != rightSide.length){
			throw new Exception("Sizes don't match");
		}
		else{
			HashMap<Character, String> ret = new HashMap<Character, String>();
			return null;
		}
	}
}
