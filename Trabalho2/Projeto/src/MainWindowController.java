import java.awt.Color;
import java.awt.Dimension;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;



@SuppressWarnings("serial")
public class MainWindowController extends JFrame {
	
	private static MainWindowController mainFrame;
	private JPanel contentPane;
	
	private final int 	frameWidth = 800,
						frameHeight = 600,
						menuBarHeight = 20,
						elmntGap = 5;
	
	public static int selection = 0;
	public static File descriptor;
	public static String message = "Open a file to start";

	/**
	 * creates the frame
	 */
	public MainWindowController() {
		JFileChooser fc = new JFileChooser();
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		DefaultListModel<String> listModel = new DefaultListModel<>();
		
		setBounds(getDefaultScreenFrameCenter().width, getDefaultScreenFrameCenter().height, frameWidth, frameHeight);
		setResizable(false);
		setTitle("L-System Viewer");
		setName("mainWindow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, frameWidth, menuBarHeight);
		contentPane.add(menuBar);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenuItem mntmCloseFile = new JMenuItem("Close File...");
		mntmCloseFile.addActionListener(new ActionListener() {
			// Close File
			@Override
			public void actionPerformed(ActionEvent e) {
				message = "File " + descriptor.getName() + " closed";
				if(descriptor != null)
					descriptor = null;
				mntmCloseFile.setEnabled(false);
			}
		});
		mntmCloseFile.setEnabled(false);
		
		JMenuItem mntmOpenFile = new JMenuItem("Open File...");
		mntmOpenFile.addActionListener(new ActionListener() {
			// Open File
			@Override
			public void actionPerformed(ActionEvent e) {
				int ret = fc.showOpenDialog(getMainFrame());
				
				if(ret == JFileChooser.APPROVE_OPTION){
					descriptor = fc.getSelectedFile();
					
					if(FileChecker.check(descriptor)){
						mntmCloseFile.setEnabled(true);
						message = descriptor.getAbsolutePath() + "\n" + "is currently open";
					}
					else{
						message = descriptor.getName() + " is an invalid archive";
						descriptor = null;
						
					}
				}
				else{
					message = ret + ": Open file operation failed";
				}
			}
			
			
		});
		mnOptions.add(mntmOpenFile);
		mnOptions.add(mntmCloseFile);
		
		mnOptions.addSeparator();
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mntmExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnOptions.add(mntmExit);
		

		
		JPanel drawingPanelContainer = new JPanel();
		drawingPanelContainer.setLayout(null);
		// X,Y,Width, Height
		drawingPanelContainer.setBounds(elmntGap, elmntGap + menuBarHeight, (int) Math.floor(frameWidth*0.75), frameHeight-menuBarHeight-2*elmntGap);
		drawingPanelContainer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		DrawingPanelController drawPanel = new DrawingPanelController(drawingPanelContainer.getWidth()-2, drawingPanelContainer.getHeight()-2);
		drawingPanelContainer.add(drawPanel.getSCanvas());
		drawingPanelContainer.getComponent(0).setLocation(1, 1);
		drawPanel.init();
		
//		//Remove Later
//		
//				drawPanel.getSCanvas().addMouseListener(new MouseListener() {
//					
//					@Override
//					public void mouseClicked(MouseEvent e) {
//						File f = new File("./Examples/01");
//						FileChecker.check(f);
//						
//					}
//
//					@Override
//					public void mousePressed(MouseEvent e) {}
//
//					@Override
//					public void mouseReleased(MouseEvent e) {}
//
//					@Override
//					public void mouseEntered(MouseEvent e) {}
//
//					@Override
//					public void mouseExited(MouseEvent e) {}
//				});
//				
//				//Remove Later
		
		contentPane.add(drawingPanelContainer);
		
		JPanel writePanel = new JPanel();
		writePanel.setName("writingPanel\n");
		writePanel.setBounds(drawingPanelContainer.getWidth() + 2*elmntGap, elmntGap + menuBarHeight, frameWidth-drawingPanelContainer.getWidth()-3*elmntGap, drawingPanelContainer.getHeight());
		writePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		writePanel.setBackground(Color.WHITE);
		writePanel.setLayout(null);
		contentPane.add(writePanel);
		
		JList<String> iterationList = new JList<String>(listModel);
		iterationList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		iterationList.setName("iterationList");
		iterationList.setFont(UIManager.getFont("List.font"));
		iterationList.setBackground(Color.WHITE);
		iterationList.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selection = iterationList.getSelectedIndex();
			}
		});
		
		JButton btnGenerate = new JButton("Generate");
		btnGenerate.setName("generateBtn");
		btnGenerate.setToolTipText("Generates next iteration");
		btnGenerate.setBounds((writePanel.getWidth()-117)/2, writePanel.getHeight()-25-elmntGap, 117, 25);
		btnGenerate.addActionListener(
			new ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					selection = listModel.getSize();
					listModel.addElement("Geração " + listModel.getSize());
					iterationList.setSelectedValue(listModel.getElementAt(listModel.getSize()-1), true);
				}
			});
		writePanel.add(btnGenerate);
		
		
		listModel.addElement("Geração 0");
		
		JScrollPane scroll = new JScrollPane(iterationList);
		scroll.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		scroll.setBounds(elmntGap, elmntGap, writePanel.getWidth()-2*elmntGap, writePanel.getHeight()-btnGenerate.getHeight()-3*elmntGap);
		writePanel.add(scroll);
	}
	
	public static Rectangle getDefaultScreenSize() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	}
	
	public Dimension getDefaultScreenFrameCenter() {
		return new Dimension((getDefaultScreenSize().width-frameWidth)/2,(getDefaultScreenSize().height-frameHeight)/2);
	}

	public static MainWindowController getMainFrame() {
		return mainFrame;
	}

	public static void setMainFrame(MainWindowController mainFrame) {
		MainWindowController.mainFrame = mainFrame;
	}
}

