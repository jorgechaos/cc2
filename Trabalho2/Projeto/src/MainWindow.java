import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

	private JPanel contentPane;
	private int frameWidth = 800, frameHeight = 600;
	private int gap = 5;
	
	public static int selection = 0;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
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
		
		JPanel drawingPanelContainer = new JPanel();
		drawingPanelContainer.setLayout(null);
		drawingPanelContainer.setBounds(gap, gap, (int) Math.floor(frameWidth*0.75), frameHeight-2*gap);
		drawingPanelContainer.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		DrawingPanel drawPanel = new DrawingPanel(drawingPanelContainer.getWidth()-2, drawingPanelContainer.getHeight()-2);
		drawingPanelContainer.add(drawPanel.getSCanvas());
		drawingPanelContainer.getComponent(0).setLocation(1, 1);
		drawPanel.init();
		
		contentPane.add(drawingPanelContainer);
		
		JPanel writePanel = new JPanel();
		writePanel.setName("writingPanel\n");
		writePanel.setBounds(drawingPanelContainer.getWidth() + 2*gap, gap, frameWidth-drawingPanelContainer.getWidth()-3*gap, drawingPanelContainer.getHeight());
		writePanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		writePanel.setBackground(Color.WHITE);
		writePanel.setLayout(null);
		contentPane.add(writePanel);
		
		JList iterationList = new JList<>(listModel);
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
		btnGenerate.setBounds((writePanel.getWidth()-117)/2, writePanel.getHeight()-25-gap, 117, 25);
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
		scroll.setBounds(gap, gap, writePanel.getWidth()-2*gap, writePanel.getHeight()-btnGenerate.getHeight()-3*gap);
		writePanel.add(scroll);
	}
	
	public static Rectangle getDefaultScreenSize() {
		return GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration().getBounds();
	}
	
	public Dimension getDefaultScreenFrameCenter() {
		return new Dimension((getDefaultScreenSize().width-frameWidth)/2,(getDefaultScreenSize().height-frameHeight)/2);
	}
}
