package ma.aui.scmtool.view;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import ma.aui.scmtool.model.CompilationUnit;
import ma.aui.scmtool.model.CuMetadata;
import ma.aui.scmtool.model.Parser;
import ma.aui.scmtool.visitor.AstExplorerVisitor;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JLabel;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.IPackageFragment;
import org.eclipse.jdt.core.IPackageFragmentRoot;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JScrollPane;

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
	private IProject project;
	private JTabbedPane tabbedPane;
	//Tabs
	private JPanel contentTab;
	private JPanel statementsTab;
	private JPanel methodTab;
	private JPanel classTab;
	private JTextArea txtsourceCode;
	private JLabel lblLinesOfCode;
	private JTextField txtloc;
	private JLabel lblNumberOfClasses;
	private JTextField txtnclasses;
	private JLabel lblNumberOfMethods;
	private JTextField txtmethods;
	private JComboBox cbsts;
	private JLabel lblStatement;
	private JLabel lblNumberOfOperators;
	private JLabel lblNumberOflevels;
	private JLabel lblMethod;
	private JLabel lblDataUsage;
	private JTextField txtstnoo;
	private JTextField txtstnl;
	private JTextField txtstdf;
	private JTextField txtstdu;
	private JTextArea textArea;
	private JTextArea textArea_1;
	private JTextField txtmtto;
	private JLabel lblTotalOfOperators;
	private JLabel lblMaxOfOperators;
	private JTextField txtmmop;
	private JLabel lblTotalOfLevels;
	private JTextField txtmtl;
	private JLabel lblMaxOfLevels;
	private JTextField txtmml;
	private JTextField txtmtdf;
	private JLabel lblTotalOfData;
	private JLabel lblMaxOfData;
	private JTextField txtmmdf;
	private JLabel lblTotalOfLevels_1;
	private JTextField txtmtdu;
	private JLabel lblMaxOfData_1;
	private JTextField txtmdu;
	private JLabel lblNumberOfMethod;
	private JTextField txtmmc;
	private JTextArea textArea_2;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_1;
	private JScrollPane scrollPane_2;
	private JScrollPane scrollPane_3;
	private JLabel lblTotalOfOperators_1;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblTotalOfLevels_2;
	private JLabel lblNumberOfMethod_1;
	private JTextField textField_2;
	private JLabel lblTotalOfMax;
	private JTextField textField_3;
	private JLabel lblMaxOfLevels_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JLabel lblTotalOfMax_1;
	private JLabel lblTotalOfMax_2;
	private JTextField textField_6;
	private JLabel lblMaxOfTotal;
	private JTextField textField_7;
	private JLabel lblMaxOfTotal_1;
	private JTextField textField_8;
	private JLabel label;
	private JTextField textField_9;
	private JLabel lblTotalOfMax_3;
	private JTextField textField_10;
	private JLabel lblMaxOfTotal_2;
	private JTextField textField_11;
	private JTextField textField_12;
	private JLabel lblTotalOfData_1;
	private JLabel lblMaxOfData_2;
	private JTextField textField_13;
	private JLabel lblTotalOfMax_4;
	private JTextField textField_14;
	private JLabel lblMaxOfTotal_3;
	private JTextField textField_15;
	private JTextField textField_16;
	private JLabel lblTotalOfData_2;
	private JLabel lblMaxOfMethod;
	private JTextField textField_17;
	private JTextField textField_18;
	private JLabel lblTotalOfInout;
	private JLabel lblMaxOfInout;
	private JTextField textField_19;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SCMGUI window = new SCMGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

				/*IWorkspaceRoot root = ResourcesPlugin.getWorkspace().getRoot();
				IProject project = root.getProject("Temp");
				try {
					project.create(null);
					project.open(null);
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				IJavaProject javaProject = JavaCore.create(project);

				IFolder binFolder = project.getFolder("bin");
				try {
					binFolder.create(false, true, null);
					javaProject.setOutputLocation(binFolder.getFullPath(), null);
				} catch (JavaModelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				List<IClasspathEntry> entries = new ArrayList<IClasspathEntry>();
				IVMInstall vmInstall = JavaRuntime.getDefaultVMInstall();
				LibraryLocation[] locations = JavaRuntime.getLibraryLocations(vmInstall);
				for (LibraryLocation element : locations) {
				 entries.add(JavaCore.newLibraryEntry(element.getSystemLibraryPath(), null, null));
				}
				//add libs to project class path
				try {
					javaProject.setRawClasspath(entries.toArray(new IClasspathEntry[entries.size()]), null);
					IFolder sourceFolder = project.getFolder("src");
					sourceFolder.create(false, true, null);


				IPackageFragmentRoot root1 = javaProject.getPackageFragmentRoot(sourceFolder);
				IClasspathEntry[] oldEntries = javaProject.getRawClasspath();
				IClasspathEntry[] newEntries = new IClasspathEntry[oldEntries.length + 1];
				System.arraycopy(oldEntries, 0, newEntries, 0, oldEntries.length);
				newEntries[oldEntries.length] = JavaCore.newSourceEntry(root1.getPath());
				javaProject.setRawClasspath(newEntries, null);
				IPackageFragment pack = javaProject.getPackageFragmentRoot(sourceFolder).createPackageFragment("ast", false, null);

				} 
				catch (CoreException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/


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
		frame.setBounds(100, 100, 1017, 638);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		contentTab = new JPanel(null);
		classTab = new JPanel(null);
		methodTab =  new JPanel(null);

		parser = new Parser(new AstExplorerVisitor());

		cuMetadata = new CuMetadata(new Vector<String>());

		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1011, 23);
		frame.getContentPane().add(menuBar);

		mnFile = new JMenu("File");
		menuBar.add(mnFile);

		mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new MntmOpenActionListener());

		mnFile.add(mntmOpen);
		cbTypesModel = new DefaultComboBoxModel<String>();
		cbTypes = new JComboBox<String>(cbTypesModel);
		//cbTypes.addItemListener(new CbTypesItemListener());
		cbTypes.setBounds(841, 35, 158, 23);
		frame.getContentPane().add(cbTypes);

		cbMethods = new JComboBox();
		cbMethods.setBounds(841, 70, 158, 23);
		frame.getContentPane().add(cbMethods);

		lblClasses = new JLabel("Class");
		lblClasses.setBounds(765, 38, 55, 16);
		frame.getContentPane().add(lblClasses);

		lblMethods = new JLabel("Method");
		lblMethods.setBounds(765, 73, 55, 16);
		frame.getContentPane().add(lblMethods);

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 24, 747, 585);
		frame.getContentPane().add(tabbedPane);
		tabbedPane.add(classTab);

		tabbedPane.addTab("File Content", contentTab);
		
		scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(0, 0, 742, 557);
		contentTab.add(scrollPane_2);

		txtsourceCode = new JTextArea();
		scrollPane_2.setViewportView(txtsourceCode);
		statementsTab = new JPanel(null);
		tabbedPane.addTab("Statements Metrics", statementsTab);
		
		lblNumberOfOperators = new JLabel("Number of Operators :");
		lblNumberOfOperators.setBounds(91, 157, 132, 16);
		statementsTab.add(lblNumberOfOperators);
		
		lblNumberOflevels = new JLabel("Number of Levels :");
		lblNumberOflevels.setBounds(253, 157, 132, 16);
		statementsTab.add(lblNumberOflevels);
		
		lblMethod = new JLabel("Data Flow :");
		lblMethod.setBounds(415, 157, 132, 16);
		statementsTab.add(lblMethod);
		
		lblDataUsage = new JLabel("Data Usage :");
		lblDataUsage.setBounds(577, 157, 132, 16);
		statementsTab.add(lblDataUsage);
		
		txtstnoo = new JTextField();
		txtstnoo.setColumns(10);
		txtstnoo.setBounds(91, 185, 71, 23);
		statementsTab.add(txtstnoo);
		
		txtstnl = new JTextField();
		txtstnl.setColumns(10);
		txtstnl.setBounds(253, 185, 71, 23);
		statementsTab.add(txtstnl);
		
		txtstdf = new JTextField();
		txtstdf.setColumns(10);
		txtstdf.setBounds(415, 185, 71, 23);
		statementsTab.add(txtstdf);
		
		txtstdu = new JTextField();
		txtstdu.setColumns(10);
		txtstdu.setBounds(577, 185, 71, 23);
		statementsTab.add(txtstdu);
		
		scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(12, 12, 697, 99);
		statementsTab.add(scrollPane_3);
		
		textArea = new JTextArea();
		scrollPane_3.setViewportView(textArea);
		tabbedPane.addTab("Method Metrics", methodTab);
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(12, 12, 697, 99);
		methodTab.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		txtmtto = new JTextField();
		txtmtto.setColumns(10);
		txtmtto.setBounds(91, 167, 71, 23);
		methodTab.add(txtmtto);
		
		lblTotalOfOperators = new JLabel("Total of Operators");
		lblTotalOfOperators.setBounds(91, 139, 132, 16);
		methodTab.add(lblTotalOfOperators);
		
		lblMaxOfOperators = new JLabel("Max of Operators");
		lblMaxOfOperators.setBounds(253, 139, 132, 16);
		methodTab.add(lblMaxOfOperators);
		
		txtmmop = new JTextField();
		txtmmop.setColumns(10);
		txtmmop.setBounds(253, 167, 71, 23);
		methodTab.add(txtmmop);
		
		lblTotalOfLevels = new JLabel("Total of Levels");
		lblTotalOfLevels.setBounds(415, 139, 132, 16);
		methodTab.add(lblTotalOfLevels);
		
		txtmtl = new JTextField();
		txtmtl.setColumns(10);
		txtmtl.setBounds(415, 167, 71, 23);
		methodTab.add(txtmtl);
		
		lblMaxOfLevels = new JLabel("Max of levels");
		lblMaxOfLevels.setBounds(577, 139, 132, 16);
		methodTab.add(lblMaxOfLevels);
		
		txtmml = new JTextField();
		txtmml.setColumns(10);
		txtmml.setBounds(577, 167, 71, 23);
		methodTab.add(txtmml);
		
		txtmtdf = new JTextField();
		txtmtdf.setColumns(10);
		txtmtdf.setBounds(91, 230, 71, 23);
		methodTab.add(txtmtdf);
		
		lblTotalOfData = new JLabel("Total of Data Flow");
		lblTotalOfData.setBounds(91, 202, 132, 16);
		methodTab.add(lblTotalOfData);
		
		lblMaxOfData = new JLabel("Max of Data Flow");
		lblMaxOfData.setBounds(253, 202, 132, 16);
		methodTab.add(lblMaxOfData);
		
		txtmmdf = new JTextField();
		txtmmdf.setColumns(10);
		txtmmdf.setBounds(253, 230, 71, 23);
		methodTab.add(txtmmdf);
		
		lblTotalOfLevels_1 = new JLabel("Total of Data Usage");
		lblTotalOfLevels_1.setBounds(415, 202, 132, 16);
		methodTab.add(lblTotalOfLevels_1);
		
		txtmtdu = new JTextField();
		txtmtdu.setColumns(10);
		txtmtdu.setBounds(415, 230, 71, 23);
		methodTab.add(txtmtdu);
		
		lblMaxOfData_1 = new JLabel("Max of Data Usage");
		lblMaxOfData_1.setBounds(577, 202, 132, 16);
		methodTab.add(lblMaxOfData_1);
		
		txtmdu = new JTextField();
		txtmdu.setColumns(10);
		txtmdu.setBounds(577, 230, 71, 23);
		methodTab.add(txtmdu);
		
		lblNumberOfMethod = new JLabel("Number of Method Calls");
		lblNumberOfMethod.setBounds(91, 265, 150, 16);
		methodTab.add(lblNumberOfMethod);
		
		txtmmc = new JTextField();
		txtmmc.setColumns(10);
		txtmmc.setBounds(91, 293, 71, 23);
		methodTab.add(txtmmc);
		tabbedPane.addTab("Class Metrics", classTab);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 697, 99);
		classTab.add(scrollPane);
		
		textArea_2 = new JTextArea();
		scrollPane.setViewportView(textArea_2);
		
		lblTotalOfOperators_1 = new JLabel("Total of Operators");
		lblTotalOfOperators_1.setBounds(65, 140, 132, 16);
		classTab.add(lblTotalOfOperators_1);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(65, 168, 71, 23);
		classTab.add(textField);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(65, 231, 71, 23);
		classTab.add(textField_1);
		
		lblTotalOfLevels_2 = new JLabel("Total of Levels");
		lblTotalOfLevels_2.setBounds(65, 203, 132, 16);
		classTab.add(lblTotalOfLevels_2);
		
		lblNumberOfMethod_1 = new JLabel("Total of Data Flow");
		lblNumberOfMethod_1.setBounds(65, 266, 150, 16);
		classTab.add(lblNumberOfMethod_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(65, 294, 71, 23);
		classTab.add(textField_2);
		
		lblTotalOfMax = new JLabel("Max of Operators");
		lblTotalOfMax.setBounds(237, 140, 168, 16);
		classTab.add(lblTotalOfMax);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(237, 168, 71, 23);
		classTab.add(textField_3);
		
		lblMaxOfLevels_1 = new JLabel("Max of Levels");
		lblMaxOfLevels_1.setBounds(237, 203, 132, 16);
		classTab.add(lblMaxOfLevels_1);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(237, 231, 71, 23);
		classTab.add(textField_4);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(393, 168, 71, 23);
		classTab.add(textField_5);
		
		lblTotalOfMax_1 = new JLabel("Total of Max of Operators");
		lblTotalOfMax_1.setBounds(393, 140, 160, 16);
		classTab.add(lblTotalOfMax_1);
		
		lblTotalOfMax_2 = new JLabel("Total of Max of Levels");
		lblTotalOfMax_2.setBounds(393, 203, 132, 16);
		classTab.add(lblTotalOfMax_2);
		
		textField_6 = new JTextField();
		textField_6.setColumns(10);
		textField_6.setBounds(393, 231, 71, 23);
		classTab.add(textField_6);
		
		lblMaxOfTotal = new JLabel("Max of Total Operators");
		lblMaxOfTotal.setBounds(577, 140, 132, 16);
		classTab.add(lblMaxOfTotal);
		
		textField_7 = new JTextField();
		textField_7.setColumns(10);
		textField_7.setBounds(577, 168, 71, 23);
		classTab.add(textField_7);
		
		lblMaxOfTotal_1 = new JLabel("Max of Total of Levels");
		lblMaxOfTotal_1.setBounds(577, 203, 132, 16);
		classTab.add(lblMaxOfTotal_1);
		
		textField_8 = new JTextField();
		textField_8.setColumns(10);
		textField_8.setBounds(577, 231, 71, 23);
		classTab.add(textField_8);
		
		label = new JLabel("Max of Data Flow");
		label.setBounds(237, 266, 132, 16);
		classTab.add(label);
		
		textField_9 = new JTextField();
		textField_9.setColumns(10);
		textField_9.setBounds(237, 294, 71, 23);
		classTab.add(textField_9);
		
		lblTotalOfMax_3 = new JLabel("Total of Max of Data Flow");
		lblTotalOfMax_3.setBounds(393, 266, 160, 16);
		classTab.add(lblTotalOfMax_3);
		
		textField_10 = new JTextField();
		textField_10.setColumns(10);
		textField_10.setBounds(393, 294, 71, 23);
		classTab.add(textField_10);
		
		lblMaxOfTotal_2 = new JLabel("Max of Total of Data Flow");
		lblMaxOfTotal_2.setBounds(577, 266, 153, 16);
		classTab.add(lblMaxOfTotal_2);
		
		textField_11 = new JTextField();
		textField_11.setColumns(10);
		textField_11.setBounds(577, 294, 71, 23);
		classTab.add(textField_11);
		
		textField_12 = new JTextField();
		textField_12.setColumns(10);
		textField_12.setBounds(65, 366, 71, 23);
		classTab.add(textField_12);
		
		lblTotalOfData_1 = new JLabel("Total of Data Usage");
		lblTotalOfData_1.setBounds(65, 338, 150, 16);
		classTab.add(lblTotalOfData_1);
		
		lblMaxOfData_2 = new JLabel("Max of Data Usage");
		lblMaxOfData_2.setBounds(237, 338, 132, 16);
		classTab.add(lblMaxOfData_2);
		
		textField_13 = new JTextField();
		textField_13.setColumns(10);
		textField_13.setBounds(237, 366, 71, 23);
		classTab.add(textField_13);
		
		lblTotalOfMax_4 = new JLabel("Total of Max of Data Usage");
		lblTotalOfMax_4.setBounds(393, 338, 160, 16);
		classTab.add(lblTotalOfMax_4);
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(393, 366, 71, 23);
		classTab.add(textField_14);
		
		lblMaxOfTotal_3 = new JLabel("Max of Total of Data Usage");
		lblMaxOfTotal_3.setBounds(577, 338, 153, 16);
		classTab.add(lblMaxOfTotal_3);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(577, 366, 71, 23);
		classTab.add(textField_15);
		
		textField_16 = new JTextField();
		textField_16.setColumns(10);
		textField_16.setBounds(65, 435, 71, 23);
		classTab.add(textField_16);
		
		lblTotalOfData_2 = new JLabel("Total of Method Calls");
		lblTotalOfData_2.setBounds(65, 407, 150, 16);
		classTab.add(lblTotalOfData_2);
		
		lblMaxOfMethod = new JLabel("Max of Method Calls");
		lblMaxOfMethod.setBounds(237, 407, 132, 16);
		classTab.add(lblMaxOfMethod);
		
		textField_17 = new JTextField();
		textField_17.setColumns(10);
		textField_17.setBounds(237, 435, 71, 23);
		classTab.add(textField_17);
		
		textField_18 = new JTextField();
		textField_18.setColumns(10);
		textField_18.setBounds(393, 435, 71, 23);
		classTab.add(textField_18);
		
		lblTotalOfInout = new JLabel("InOut Degree");
		lblTotalOfInout.setBounds(393, 407, 150, 16);
		classTab.add(lblTotalOfInout);
		
		lblMaxOfInout = new JLabel("Number Of Public Members");
		lblMaxOfInout.setBounds(565, 407, 165, 16);
		classTab.add(lblMaxOfInout);
		
		textField_19 = new JTextField();
		textField_19.setColumns(10);
		textField_19.setBounds(565, 435, 71, 23);
		classTab.add(textField_19);
		
				lblLinesOfCode = new JLabel("Lines of Code : ");
				lblLinesOfCode.setBounds(765, 165, 86, 26);
				frame.getContentPane().add(lblLinesOfCode);
				
						txtloc = new JTextField();
						txtloc.setBounds(928, 167, 71, 23);
						frame.getContentPane().add(txtloc);
						txtloc.setColumns(10);
						
								lblNumberOfClasses = new JLabel("Number of Classes : ");
								lblNumberOfClasses.setBounds(765, 205, 120, 16);
								frame.getContentPane().add(lblNumberOfClasses);
								
										txtnclasses = new JTextField();
										txtnclasses.setBounds(928, 202, 71, 23);
										frame.getContentPane().add(txtnclasses);
										txtnclasses.setColumns(10);
										
												txtmethods = new JTextField();
												txtmethods.setBounds(928, 237, 71, 23);
												frame.getContentPane().add(txtmethods);
												txtmethods.setColumns(10);
												
														lblNumberOfMethods = new JLabel("Number of Methods : ");
														lblNumberOfMethods.setBounds(765, 240, 120, 16);
														frame.getContentPane().add(lblNumberOfMethods);
														
														cbsts = new JComboBox();
														cbsts.setBounds(841, 110, 158, 23);
														frame.getContentPane().add(cbsts);
														
														lblStatement = new JLabel("Statement");
														lblStatement.setBounds(765, 113, 71, 16);
														frame.getContentPane().add(lblStatement);
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

				try{
					FileReader reader = new FileReader(selectedFile);
					BufferedReader br = new BufferedReader(reader);
					txtsourceCode.read(br, null);
					br.close();
					//txtsourceCode.requestFocus();
				}

				//lines of code

				catch(Exception e2){
					System.out.println(e2); 
				}

				try
				{
					parser.parseFile(selectedFile);

				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}

				//lines of code
				txtloc.setText(parser.getLoc().toString());

				//populating the classes cb

				System.out.println (parser.getAstExplorerVisitor().getCunitsList().size());
				CompilationUnit cu = parser.getAstExplorerVisitor().getCunitsList().firstElement();
				Vector<ma.aui.scmtool.model.Class> vclasses = (Vector) cu.getClasses();
				Vector<String> classesNames = new Vector<String>();

				for(ma.aui.scmtool.model.Class clazz : vclasses){

					TypeDeclaration td = (TypeDeclaration) clazz.getNode();
					String className = td.getName().toString();
					classesNames.add(className);
				}

				cbTypesModel = new DefaultComboBoxModel<String>(classesNames);
				cbTypes.setModel(cbTypesModel);

				//setnumber ofClasses
				
				txtnclasses.setText(Integer.toString(vclasses.size()));




			}
		}
	}
}
