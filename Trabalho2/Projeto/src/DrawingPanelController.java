import processing.awt.PSurfaceAWT.SmoothCanvas;
import processing.core.*;

public class DrawingPanelController extends PApplet {
	
	private SmoothCanvas smoothCanvas;
	private PSurface ps;
	
	private int Width, Height;
	
	public DrawingPanelController(int w, int h){
		Width = w;
		Height = h;
		
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

	public void setup() {
		
	}

	public void draw() {
		background(255);
		fill(0);
		text("Geração " + MainWindowController.selection, width-100, 20);
		text(MainWindowController.message, 5, 20);
		
		fill(55,255,255);
		ellipse(Width/2,Height/2,10*MainWindowController.selection+1,10*MainWindowController.selection+1);
	}
}
