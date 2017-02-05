import java.util.HashMap;

public class LSystem {
	private AlphabetTable alphabetTable;
	private String axiom;
	private HashMap<Character, String> rules;
	private HashMap<Integer, String> generation;
	private Integer generated;
	
	private float len;
	private float ang;
	
	public LSystem() {
		this.alphabetTable = new AlphabetTable();
		this.axiom = "";
		this.rules = new HashMap<>();
		this.generated = 0;
		
		this.generation = new HashMap<>();
		this.generation.put(generated, this.getAxiom());
		
		this.len = 10;
		this.ang = 60;
	}

	public LSystem (AlphabetTable at, String ax, HashMap<Character, String> rls){
		this.alphabetTable = at;
		this. axiom = ax;
		this.rules = rls;
		
		this.generation = new HashMap<>();
		this.generation.put(generated, this.getAxiom());
		
		this.len = 10;
		this.ang = 60;
	}
	
	public boolean hasRuleFor(Character c) {
		return rules.containsKey(c);
	}
	
	public void addRule(Character c, String rule) {
		this.rules.put(c, rule);
	}
	
	public void newGeneration() {
		String oldGen = generation.get(generated);
		
		String thisGen = "";
		
		for(int i =0; i < oldGen.length(); i++) {
			if (hasRuleFor(oldGen.charAt(i))){
				thisGen += rules.get(oldGen.charAt(i));
			}
			else
				thisGen += oldGen.charAt(i);
		}
		
		generated++;
		generation.put(generated, thisGen);
	}
	
	public AlphabetTable getAlpTab() {
		return alphabetTable;
	}

	public void setAlpTab(AlphabetTable at) {
		this.alphabetTable = at;
	}

	public String getGeneration(Integer n) {
		return generation.get(n);
	}

	public String getAxiom() {
		return axiom;
	}

	public void setAxiom(String axiom) {
		this.axiom = axiom;
		this.generation.put(0, axiom);
	}
	
	public Integer getLastGenNmbr() {
		return generated;
	}

	public float getLen() {
		return len;
	}

	public void setLen(float len) {
		this.len = len;
	}

	public float getAng() {
		return ang;
	}

	public void setAng(float ang) {
		this.ang = ang;
	}
}
