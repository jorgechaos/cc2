import java.awt.EventQueue;

import controller.MainWindowController;

public class Main {
	/**
	 * launches the application
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindowController.setMainFrame(new MainWindowController());
					MainWindowController.getMainFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
