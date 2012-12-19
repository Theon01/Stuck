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
import org.eclipse.jdt.launching.IVMInstall;
import org.eclipse.jdt.launching.JavaRuntime;
import org.eclipse.jdt.launching.LibraryLocation;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JTabbedPane;

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
		//cbTypes.addItemListener(new CbTypesItemListener());
		cbTypes.setBounds(369, 117, 158, 23);
		frame.getContentPane().add(cbTypes);

		cbMethods = new JComboBox();
		cbMethods.setBounds(369, 151, 158, 23);
		frame.getContentPane().add(cbMethods);

		lblClasses = new JLabel("Class");
		lblClasses.setBounds(310, 120, 55, 16);
		frame.getContentPane().add(lblClasses);

		lblMethods = new JLabel("Method");
		lblMethods.setBounds(310, 154, 55, 16);
		frame.getContentPane().add(lblMethods);
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 215, 887, 269);
		frame.getContentPane().add(tabbedPane);
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

				System.out.println(selectedFile.getAbsolutePath());

				//populating the classes cb
				/*	cuMetadata = parser.getAstExplorerVisitor().getCuMetadata();
				System.out.println(cuMetadata.getTypesNames().toString()+"gui");

				cbTypesModel = new DefaultComboBoxModel<String>(cuMetadata.getTypesNames());
				cbTypes.setModel(cbTypesModel);*/

				//cbTypesModel.addElement("test");
			}
		}
	}
}
