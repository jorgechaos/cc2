import processing.awt.PSurfaceAWT.SmoothCanvas;
import processing.core.PApplet;
import processing.core.PSurface;

public class DrawingPanelController extends PApplet {
	
	private SmoothCanvas smoothCanvas;
	private PSurface ps;
	
	public DrawingPanelController(int w, int h){
		ps = initSurface();
		smoothCanvas = (SmoothCanvas)ps.getNative();
		setSurfNCanvSize(w, h);
	}
	
	public SmoothCanvas getSCanvas(){
		return this.smoothCanvas;
	}
	
	public void setSurfNCanvSize(int w, int h){
		ps.setSize(w, h);
		smoothCanvas.setSize(w, h);
	}
	
	public void init(){
		ps.startThread();
	}
	
// Processing code

	private DrawingPanelMessages m;
	private Turtle t;
	
	public void setup() {
		m = new DrawingPanelMessages(this);
		t = new Turtle(this);
	}
	
	public void draw() {
		background(255);
		m.display();
		t.turtle();
	}
	
	public Turtle getTurtle() {
		return this.t;
	}
}
