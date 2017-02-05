import processing.core.PApplet;
import processing.core.PMatrix;

public class Turtle extends PApplet {
	private PApplet p;

	private LSystem ls;
	private String sentence;
	private float len;
	private float ang;

	public Turtle (PApplet p) {
		this.p = p;
		setSize(p.width, p.height);
		
		this.ls = new LSystem();
		this.sentence = "";
		
		this.len = ls.getLen();
		this.ang = ls.getAng();
	}

	public void turtle() {
		p.translate(p.width/2, p.height);
		p.stroke(0);
		p.strokeWeight(2/MainWindowController.zoom);
		for (int i=0; i < getSentence().length(); i++) {
			Character current = getSentence().charAt(i);

			if (getLSystem().getAlpTab().hasKey(current)) {

				switch (getLSystem().getAlpTab().get(current)) {
				
				case FORGET:
					PMatrix tmp = p.getMatrix();
					p.popMatrix();
					p.setMatrix(tmp);
					break;
					
				case FORWARD:
					p.line(0, 0, 0, (-len)/MainWindowController.zoom);
					p.translate(0, (-len)/MainWindowController.zoom);
					break;
					
				case FORWARD_NODRAW:
					p.translate(0, (-len)/MainWindowController.zoom);
					break;
					
				case POP:
					p.popMatrix();
					break;
					
				case PUSH:
					p.pushMatrix();
					break;
					
				case RESET:
					p.resetMatrix();
					break;
				
				case ROTATE_CCW:
					p.rotate(radians(ang));
					break;
				
				case ROTATE_CW:
					p.rotate(radians(-ang));
					break;
				
				case NONE:
				default:
					break;
				}
			}
		}
		
		p.noLoop();
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
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

	public LSystem getLSystem() {
		return ls;
	}

	public void setLSystem(LSystem ls) {
		this.ls = ls;
	}
	
	public void reset(LSystem ls) {
		this.ls = ls;
		this.sentence = "";
		
		this.len = ls.getLen();
		this.ang = ls.getAng();
	}

}
