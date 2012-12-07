package ma.aui.scmtool.view;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import ma.aui.scmtool.model.CuMetadata;
import ma.aui.scmtool.model.Parser;
import ma.aui.scmtool.visitor.AstExplorerVisitor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;


public class SCMGUI 
{
	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmOpen;
	private JFileChooser fileChooser;
	private File selectedFile;
	private JComboBox<String> cbTypes;
	private JComboBox cbMethods;
	private JLabel lblClasses;
	private JLabel lblMethods;
	private DefaultComboBoxModel<String> cbTypesModel ;
	private CuMetadata cuMetadata;
	private Parser parser;
	
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
		
		parser = new Parser(new AstExplorerVisitor());
		
		cuMetadata = new CuMetadata(new Vector<String>());

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 881, 23);
		frame.getContentPane().add(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new MntmOpenActionListener());
		
		mnFile.add(mntmOpen);
		cbTypesModel = new DefaultComboBoxModel<String>();
		cbTypes = new JComboBox<String>(cbTypesModel);
		cbTypes.addItemListener(new CbTypesItemListener());
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
	
	//File -> Open 
	private class MntmOpenActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
			fileChooser = new JFileChooser();
			FileFilter filter = new FileNameExtensionFilter("Java files", "java");
			//fileChooser.addChoosableFileFilter(filter);
			fileChooser.setFileFilter(filter);
			int returnValue = fileChooser.showOpenDialog(null);
			if (returnValue == JFileChooser.APPROVE_OPTION) {
				selectedFile = fileChooser.getSelectedFile();
				/********
				 * 
				 * FILE IS OPENED 
				 * 
				 *******/
				System.out.println(selectedFile.getName());
				
				try
				{
					parser.parseFile(selectedFile);
					
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
				
				//populating the classes cb
			/*	cuMetadata = parser.getAstExplorerVisitor().getCuMetadata();
				System.out.println(cuMetadata.getTypesNames().toString()+"gui");
			
				cbTypesModel = new DefaultComboBoxModel<String>(cuMetadata.getTypesNames());
				cbTypes.setModel(cbTypesModel);*/
			
				//cbTypesModel.addElement("test");
			}
		}
	}
	private class CbTypesItemListener implements ItemListener {
		public void itemStateChanged(ItemEvent e) {
			
			
			
			
		}
	}
}
