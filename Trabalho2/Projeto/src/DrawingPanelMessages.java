import processing.core.PApplet;

public class DrawingPanelMessages extends PApplet {
	
	private final PApplet p;
	
	public DrawingPanelMessages(PApplet p) {
		this.p = p;
		setSize(p.width, p.height);
	}
	
	public void display() {
		p.fill(0);
		p.text("Geração " + MainWindowController.selection, width-100, 20);
		p.text(MainWindowController.message, 5, 20);
	}
}
