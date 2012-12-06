package ma.aui.scmtool.view;
import java.awt.EventQueue;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import ma.aui.scmtool.model.Parser;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JComboBox;
import javax.swing.JLabel;


public class SCMGUI 
{
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JFileChooser fileChooser;
	private File selectedFile;
	private JComboBox cbTypes;
	private JComboBox cbMethods;
	private JLabel lblClasses;
	private JLabel lblMethods;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SCMGUI window = new SCMGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SCMGUI() 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 903, 513);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 881, 23);
		frame.getContentPane().add(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				fileChooser = new JFileChooser();
				FileFilter filter = new FileNameExtensionFilter("Java files", "java");
				fileChooser.addChoosableFileFilter(filter);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					System.out.println(selectedFile.getName());
					//metrics = new Metric();
					//parser = new Parser();

					try
					{
						Parser.parseFile(selectedFile);
						
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
				}			

			}
		});
		mnFile.add(mntmOpen);
		
		cbTypes = new JComboBox();
		cbTypes.setBounds(372, 117, 158, 23);
		frame.getContentPane().add(cbTypes);
		
		cbMethods = new JComboBox();
		cbMethods.setBounds(372, 151, 158, 23);
		frame.getContentPane().add(cbMethods);
		
		lblClasses = new JLabel("Class");
		lblClasses.setBounds(310, 120, 55, 16);
		frame.getContentPane().add(lblClasses);
		
		lblMethods = new JLabel("Method");
		lblMethods.setBounds(310, 154, 55, 16);
		frame.getContentPane().add(lblMethods);
	}
}
