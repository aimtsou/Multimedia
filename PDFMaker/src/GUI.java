import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Box;
import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.text.JTextComponent;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

//import org.apache.pdfbox.exceptions.COSVisitorException;

public class GUI {

	private JFrame frame;
	private JList<String> list;
	private String currentTextAreaText = "";
	private List<FileStatus> filesList = new ArrayList<FileStatus>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		// setting up the Frame
		frame = new JFrame();
		frame.setTitle("MediaLab PDF Editor");
		frame.setBounds(100, 100, 750, 520);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		final JTextArea textArea = new JTextArea();
		final JLabel lblStatusBar = new JLabel("Application Launched");
	
		// Listen for changes in the text
		textArea.getDocument().addDocumentListener(new DocumentListener() {
		  public void changedUpdate(DocumentEvent e) {
		  }
		  public void removeUpdate(DocumentEvent e) {
			  //System.out.println(filesList.get(list.getSelectedIndex()).getFileName());
			  if (!filesList.isEmpty()) {
				  filesList.get(list.getSelectedIndex()).setContent(textArea.getText());
				  filesList.get(list.getSelectedIndex()).setChanged(true);
			  }
		  }
		  public void insertUpdate(DocumentEvent e) {
			  if (!filesList.isEmpty()) {
				  filesList.get(list.getSelectedIndex()).setContent(textArea.getText());
				  filesList.get(list.getSelectedIndex()).setChanged(true);
			  }
		  }

		});
		
 		/*textArea.addFocusListener(new FocusAdapter() { 
 			int currentSelectedIndex;
 			public void focusGained(FocusEvent e) {
 				currentSelectedIndex = list.getSelectedIndex();
 			}
 			public void focusLost(FocusEvent arg0) { 
				if (!filesList.get(currentSelectedIndex).getContent().equals(((JTextArea)arg0.getSource()).getText())){
					filesList.get(currentSelectedIndex).setContent(((JTextArea)arg0.getSource()).getText()); 
					filesList.get(currentSelectedIndex).setChanged(true);
 				}
 			}
 		});*/
 		
		JToolBar toolBar = new JToolBar();

		final DefaultListModel<String> model;

        list = new JList<>(model = new DefaultListModel<String>());
        list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
                if (!model.isEmpty())
                {
                    int selectedIndex = list.getSelectedIndex();
                    //System.out.println(selectedIndex);
                    if (selectedIndex == -1)
                    {
                        selectedIndex = 0;
                        list.setSelectedIndex(selectedIndex);
                    }
                    //textArea.setText(filesList.get(selectedIndex).getContent());
                    if (filesList.size() > 0)
                    {
                        FileStatus fileStat = filesList.get(selectedIndex);
                        textArea.setText(fileStat.getContent());
                    }
                   
                    String filename = list.getSelectedValue();
                    String txt = null;
                    try {
                        txt = Functionality.updateStatus(filename);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    lblStatusBar.setText(txt);
                }
                else
                {
                    // Check that the filesList is also empty, otherwise remove all
                    if (!filesList.isEmpty())
                    {
                        filesList.clear();
                    }
                    
                    lblStatusBar.setText("");
                    textArea.setText("");
                }
			}
        	
        });

		JScrollPane scroll = new JScrollPane(textArea, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.getContentPane().add(scroll);

		// setting Group Layout as to be resizable
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(list, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE))
				.addComponent(lblStatusBar, GroupLayout.DEFAULT_SIZE, 732, Short.MAX_VALUE));
		groupLayout
				.setVerticalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(
										groupLayout.createSequentialGroup()
												.addComponent(toolBar, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(ComponentPlacement.RELATED)
												.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
														.addComponent(scroll, GroupLayout.DEFAULT_SIZE, 397,
																Short.MAX_VALUE)
												.addComponent(list, GroupLayout.DEFAULT_SIZE, 397, Short.MAX_VALUE))
				.addGap(4).addComponent(lblStatusBar)));

		frame.getContentPane().setLayout(groupLayout);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu menuFile = new JMenu("File");
		menuBar.add(menuFile);

		JMenuItem mntmNew = new JMenuItem("New");
		menuFile.add(mntmNew);
		mntmNew.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Component value = null;
				String filename = JOptionPane.showInputDialog(value, "Please enter a filename:");
				if (filename != null) {
					FileStatus mlabFile = new FileStatus(filename);
                	mlabFile.setChanged(true);
                	filesList.add(mlabFile);

					// update list with filename
					model.addElement(filename + ".mlab");
					//System.out.println(list.getModel().getSize());
					list.setSelectedIndex(list.getModel().getSize()-1);
					//list.setModel(model);

                	// update the status bar
					lblStatusBar.setText("New File Created: " + filename + ".mlab");
				}
				else {
					lblStatusBar.setText("User Cancelled New File Creation or did not provide a filename!");
				}
			}
		});

		JMenuItem mntmOpen = new JMenuItem("Open");
		menuFile.add(mntmOpen);
		mntmOpen.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String filename = null;
                JFileChooser filechooser = new JFileChooser();
                File workingDirectory = new File(System.getProperty("user.dir"));
                filechooser.setCurrentDirectory(workingDirectory);
                filechooser.setDialogTitle("Choose mlab files");
                filechooser.setFileFilter(new FileTypeFilter(".mlab", "mlab file"));
                filechooser.setMultiSelectionEnabled(true);
                int status = filechooser.showOpenDialog(new JFrame());
                if (status == JFileChooser.APPROVE_OPTION) {
                	File[] selectedFiles = filechooser.getSelectedFiles();
                	for (File f : selectedFiles){
                		filename = f.getName();
                		String fullFilename = f.getAbsolutePath();
                		model.addElement(filename);
                		
                		// Create the object and get the content
                    	FileStatus mlabFile = new FileStatus(filename);
                    	Functionality.GetFileContent(fullFilename, mlabFile);
                    	mlabFile.setChanged(false);
                    	filesList.add(mlabFile);
                	}
                	
                	// Select (and show) the last file
                	list.setSelectedIndex(list.getModel().getSize()-1);
                	lblStatusBar.setText("New File Opened: " + filename);
                }
                else {
                	lblStatusBar.setText("User Cancelled Open File function!");
                }
			}
		});

		JMenuItem mntmSave = new JMenuItem("Save");
		menuFile.add(mntmSave);
		mntmSave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String selected = list.getSelectedValue();
				String txt = textArea.getText();
				try {
					boolean success = Functionality.SaveFile(selected, txt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				lblStatusBar.setText("File Saved: " + selected);
			}
		});

		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		menuFile.add(mntmSaveAs);
		mntmSaveAs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String txt = textArea.getText();
				Component value = null;
				String filename = JOptionPane.showInputDialog(value, "Please enter a filename:");
				try {
					boolean result = Functionality.SaveFile(filename+".mlab", txt);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	FileStatus mlabFile1 = new FileStatus(filename);
            	mlabFile1.setChanged(true);
            	filesList.add(mlabFile1);
            	mlabFile1.setContent(txt); 
				// update list with filename
                model.addElement(filename + ".mlab");
                list.setSelectedIndex(list.getModel().getSize()-1);
                //list.setModel(model);
                lblStatusBar.setText("New File Saved As: " + filename);
			}
		});

		JMenuItem mntmClose = new JMenuItem("Close");
		menuFile.add(mntmClose);
		mntmClose.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String selected = list.getSelectedValue();
				//System.out.println(selected);
				//FileStatus mlabFile1 = null;
            	//filesList.remove(selected);
            	//mlabFile1.setContent(""); 
				// update list with filename
				int index = list.getSelectedIndex();
				//System.out.println(index);
                //model.removeElement(index);
                model.removeElement(selected);
                list.setSelectedIndex(index-1);
			}
		});

		JMenuItem mntmExit = new JMenuItem("Exit");
		menuFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				/*boolean changesExist = false;
				for (FileStatus fileStatus : filesList)
				{
					if (fileStatus.isChanged())
					{
						changesExist = true;
						break;
					}
				}
				
				if (changesExist)
				{
					int answer = JOptionPane.showConfirmDialog(null, "Changes were detected, do you want to exit anyway?");

					if (answer == JOptionPane.YES_OPTION)
					{
						frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					}
				}
				else
				{
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				}*/
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});

		JMenu menuEdit = new JMenu("Edit");
		menuBar.add(menuEdit);

		JMenuItem mntmCreatePdf = new JMenuItem("Create PDF");
		menuEdit.add(mntmCreatePdf);
		mntmCreatePdf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
	            //String pdfContent[] = textArea.getText().split("\\r?\\n");
	            //ArrayList<String>arrList = new ArrayList<>(Arrays.asList(pdfContent));
	            //System.out.println(arrList);
				//File workingDirectory = new File(System.getProperty("user.dir"));
				String selected = list.getSelectedValue();
			    /*Path wiki_path = Paths.get("E:\\eclipse\\workspace4\\PDFMaker", selected);

			    Charset charset = Charset.forName("ISO-8859-1");
			    try {
			      List<String> lines = Files.readAllLines(wiki_path, charset);
			      
			      for (String line : lines) {
			        System.out.println(line);
			      }
			      Functionality.CreatePDF(lines);
			    } catch (IOException e2) {
			      System.out.println(e2);
			    }*/
				try {
					Functionality.CreatePDF(selected);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JMenuItem mntmOnlineContent = new JMenuItem("Online Content");
		menuEdit.add(mntmOnlineContent);
		mntmOnlineContent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
        		Component value = null;
        		Object movietitle = null;
                movietitle = JOptionPane.showInputDialog(value, "Enter new Filename", "test");
                textArea.setText("");
                if (movietitle != null) {
	                String txt = Functionality.OnlineContent(movietitle);
	                textArea.append(txt);
	                lblStatusBar.setText("Movie Information Appended to textArea!");
                }
                else
                	lblStatusBar.setText("User Cancelled Online Content Command!");
			}
		});

		JMenuItem mntmOnlinePDF = new JMenuItem("Online PDF");
		menuEdit.add(mntmOnlinePDF);
		mntmOnlinePDF.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
        		Component value = null;
        		Object movietitle = null;
                movietitle = JOptionPane.showInputDialog(value, "Enter new Filename", "test");
                String txt = Functionality.OnlineContent(movietitle);
                try {
					Functionality.SaveFile("online.mlab", txt);
					Functionality.CreatePDF("online.mlab");
					Functionality.Delete("online.mlab");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		JMenuItem mntmMergePdfs = new JMenuItem("Merge PDFs");
		menuEdit.add(mntmMergePdfs);
		mntmMergePdfs.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean success = false;
				JFileChooser filechooser = new JFileChooser();
				filechooser.setDialogTitle("Choose pdf files");
				filechooser.setFileFilter(new FileTypeFilter(".pdf", "mlab file"));
				filechooser.setMultiSelectionEnabled(true);
				int status = filechooser.showOpenDialog(null);
				if (status == JFileChooser.APPROVE_OPTION) {
					File[] files = filechooser.getSelectedFiles();
					if (files.length > 1) {
		                File dir = filechooser.getCurrentDirectory();
		                Component value = null;
		                Object outputFilename = JOptionPane.showInputDialog(value, "Enter new Filename", "test");
						success = Functionality.MergeFiles(files, outputFilename, dir);
					}
					if (success) {
						JOptionPane.showMessageDialog(null, "Operation Successful", "Info", JOptionPane.INFORMATION_MESSAGE);
				    }
					else {
						JOptionPane.showMessageDialog(null, "Operation Failed", "Warning", JOptionPane.INFORMATION_MESSAGE);
					}
				} else if (status == JFileChooser.CANCEL_OPTION) {
					lblStatusBar.setText("User Cancelled Merge Command!");
				}
			}
		});

		JMenuItem mntmExtractAllPages = new JMenuItem("Extract All Pages");
		menuEdit.add(mntmExtractAllPages);
		mntmExtractAllPages.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
                JFileChooser filechooser = new JFileChooser();
                filechooser.setDialogTitle("Choose PDF file");
                filechooser.setFileFilter(new FileTypeFilter(".pdf", "PDF file"));
                int status = filechooser.showOpenDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                	File selected = filechooser.getSelectedFile();
                	//System.out.println("file: " + selected);
                	File tmp = filechooser.getCurrentDirectory();
                	boolean success = Functionality.ExtractAllPages(selected, tmp);
					if (success) {
						JOptionPane.showMessageDialog(null, "Operation Successful", "Info", JOptionPane.INFORMATION_MESSAGE);
				    }
					else {
						JOptionPane.showMessageDialog(null, "Operation Failed", "Warning", JOptionPane.INFORMATION_MESSAGE);
					}
                } else if (status == JFileChooser.CANCEL_OPTION) {
					lblStatusBar.setText("User Cancelled Exctract All Command!");
				}
			}
		});

		JMenuItem mntmExtractPages = new JMenuItem("Extract Pages");
		menuEdit.add(mntmExtractPages);
		mntmExtractPages.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean success = false;
                JFileChooser filechooser = new JFileChooser();
                filechooser.setDialogTitle("Choose PDF file");
                filechooser.setFileFilter(new FileTypeFilter(".pdf", "PDF file"));
                int status = filechooser.showOpenDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                	File selected = filechooser.getSelectedFile();
                	//System.out.println("file: " + selected);
                	File dir = filechooser.getCurrentDirectory();
                	
                	
			      JTextField xField = new JTextField(5);
			      JTextField yField = new JTextField(5);

			      JPanel myPanel = new JPanel();
			      myPanel.add(new JLabel("Start page:"));
			      myPanel.add(xField);
			      myPanel.add(Box.createHorizontalStrut(15)); // a spacer
			      myPanel.add(new JLabel("End page:"));
			      myPanel.add(yField);

			      int result = JOptionPane.showConfirmDialog(null, myPanel, 
			               "Please Enter Start and End Values", JOptionPane.OK_CANCEL_OPTION);
			      int start = Integer.parseInt(xField.getText());
			      int end = Integer.parseInt(yField.getText());
			      try {
					success = Functionality.ExtractPages(selected, dir, start, end);
			      } catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
			      }
				  if (success) {
					  JOptionPane.showMessageDialog(null, "Operation Successful", "Info", JOptionPane.INFORMATION_MESSAGE);
				  }
				  else {
					  JOptionPane.showMessageDialog(null, "Operation Failed", "Warning", JOptionPane.INFORMATION_MESSAGE);
				  }
				  
                } else if (status == JFileChooser.CANCEL_OPTION) {
					lblStatusBar.setText("User Cancelled Extract  Command!");
				}
			}
			
		});

		JMenuItem mntmSplitPages = new JMenuItem("Split Pages");
		menuEdit.add(mntmSplitPages);
		mntmSplitPages.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean success = false;
				Component value = null;
                JFileChooser filechooser = new JFileChooser();
                filechooser.setDialogTitle("Choose PDF file");
                filechooser.setFileFilter(new FileTypeFilter(".pdf", "PDF file"));
                int status = filechooser.showOpenDialog(null);
                if (status == JFileChooser.APPROVE_OPTION) {
                	File selected = filechooser.getSelectedFile();
                	File dir = filechooser.getCurrentDirectory();
                	String result = JOptionPane.showInputDialog(value, "Enter split page", 1);
                	int start = 0;
                	int end = Integer.parseInt(result);
                	try {
						success = Functionality.SplitPages(selected, dir, start, end);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
                else
                	lblStatusBar.setText("User Cancelled Split Command!");
			}
		});

		JMenu menuHelp = new JMenu("Help");
		menuBar.add(menuHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		menuHelp.add(mntmAbout);
		mntmAbout.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String titleBar = "About";
				String infoMessage = "PDF Maker developed by Aimilios Tsouvelekakis";
		        JOptionPane.showMessageDialog(null, infoMessage, titleBar, JOptionPane.INFORMATION_MESSAGE);
			}
			
		});

		JButton btnHeading = new JButton("Heading1");
		toolBar.add(btnHeading);
		btnHeading.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel = new JPanel();
				String[] choices = {"Times", "Helvetica", "Courrier"};
				JComboBox list = new JComboBox(choices); 
				list.setSelectedIndex(1); 
				panel.add(list);
				JTextField text = new JTextField(50);
				panel.add(text);
				int value = JOptionPane.showConfirmDialog(frame, panel, "Heading 1: Set Text and Choose Font", JOptionPane.OK_CANCEL_OPTION);
				if (value == JOptionPane.OK_OPTION) {
					String txt = text.getText();
					int fontchoice = list.getSelectedIndex();
					//System.out.println(fontchoice);
				    String heading = Functionality.Heading1(fontchoice, txt);
				    textArea.append(heading);
				}
			}
		});

		JButton btnHeading_1 = new JButton("Heading2");
		toolBar.add(btnHeading_1);
		btnHeading_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {			
				JPanel panel = new JPanel();
				String[] choices = {"Times", "Helvetica", "Courrier"};
				JComboBox list = new JComboBox(choices); 
				list.setSelectedIndex(1); 
				panel.add(list);
				JTextField text = new JTextField(50);
				panel.add(text);
				int value = JOptionPane.showConfirmDialog(frame, panel, "Heading 2: Set Text and Choose Font", JOptionPane.OK_CANCEL_OPTION);
				if (value == JOptionPane.OK_OPTION) {
					String txt = text.getText();
					int fontchoice = list.getSelectedIndex();
					//System.out.println(fontchoice);
				    String heading = Functionality.Heading2(fontchoice, txt);
				    textArea.append(heading);
				}
			}
		});

		JButton btnHeading_2 = new JButton("Heading3");
		toolBar.add(btnHeading_2);
		btnHeading_2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				JPanel panel = new JPanel();
				String[] choices = {"Times", "Helvetica", "Courrier"};
				JComboBox list = new JComboBox(choices); 
				list.setSelectedIndex(1); 
				panel.add(list);
				JTextField text = new JTextField(50);
				panel.add(text);
				int value = JOptionPane.showConfirmDialog(frame, panel, "Heading 3: Set Text and Choose Font", JOptionPane.OK_CANCEL_OPTION);
				if (value == JOptionPane.OK_OPTION) {
					String txt = text.getText();
					int fontchoice = list.getSelectedIndex();
					//System.out.println(fontchoice);
				    String heading = Functionality.Heading3(fontchoice, txt);
				    textArea.append(heading);
				}
			}
		});

		JButton btnHeading_3 = new JButton("Heading4");
		toolBar.add(btnHeading_3);
		btnHeading_3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				String[] choices = {"Times", "Helvetica", "Courrier"};
				JComboBox list = new JComboBox(choices); 
				list.setSelectedIndex(1); 
				panel.add(list);
				JTextField text = new JTextField(50);
				panel.add(text);
				int value = JOptionPane.showConfirmDialog(frame, panel, "Heading 4: Set Text and Choose Font", JOptionPane.OK_CANCEL_OPTION);
				if (value == JOptionPane.OK_OPTION) {
					String txt = text.getText();
					int fontchoice = list.getSelectedIndex();
					//System.out.println(fontchoice);
				    String heading = Functionality.Heading4(fontchoice, txt);
				    textArea.append(heading);
				}
			}
		});

		JButton btnParagraph = new JButton("Paragraph");
		toolBar.add(btnParagraph);
		btnParagraph.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				String[] fontchoices = {"Times", "Helvetica", "Courrier"};
				String[] fontstyle = {"Regular", "Bold", "Italic", "Bold Italic"};
				String[] fontcolour = {"Black", "Blue", "Red", "Yellow"};
				//JList list1 = new JList(fontchoices);
				JComboBox comboList1 = new JComboBox(fontchoices); 
				comboList1.setSelectedIndex(1); 
				panel.add(comboList1);
				JComboBox comboList2 = new JComboBox(fontstyle); 
				comboList2.setSelectedIndex(1); 
				panel.add(comboList2);
				JComboBox comboList3 = new JComboBox(fontcolour); 
				comboList3.setSelectedIndex(1); 
				panel.add(comboList3);
				panel.add(new JLabel("Font Size:"));
				JTextField field1 = new JTextField(10);
				panel.add(field1);
				panel.add(new JLabel("Text:"));
				JTextField field2 = new JTextField(50);
				panel.add(field2);
				int value = JOptionPane.showConfirmDialog(frame, panel, "Enter text", JOptionPane.OK_CANCEL_OPTION);
				if (value == JOptionPane.OK_OPTION)
				{
				    // OK was pressed
				    String s1 = field1.getText();
				    String s2 = field2.getText();
				    int v1 = comboList1.getSelectedIndex()+1;
				    int v2 = comboList2.getSelectedIndex()+1;
				    int v3 = comboList3.getSelectedIndex()+1;
				    System.out.println(v1);
				    System.out.println(v2);
				    System.out.println(v3);
				    // handle them
				    String paragraph = Functionality.Paragraph(s1, s2, v1, v2, v3);
				    textArea.append(paragraph);
				}
			}
		});

		JButton btnFormat = new JButton("Format");
		toolBar.add(btnFormat);
		btnFormat.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				String[] fontchoices = {"Times", "Helvetica", "Courrier"};
				String[] fontstyle = {"Regular", "Bold", "Italic", "Bold Italic"};
				String[] fontcolour = {"Black", "Blue", "Red", "Yellow"};
				//JList list1 = new JList(fontchoices);
				JComboBox comboList1 = new JComboBox(fontchoices); 
				comboList1.setSelectedIndex(1); 
				panel.add(comboList1);
				JComboBox comboList2 = new JComboBox(fontstyle); 
				comboList2.setSelectedIndex(1); 
				panel.add(comboList2);
				JComboBox comboList3 = new JComboBox(fontcolour); 
				comboList3.setSelectedIndex(1); 
				panel.add(comboList3);
				panel.add(new JLabel("Font Size:"));
				JTextField field1 = new JTextField(10);
				panel.add(field1);
				panel.add(new JLabel("Text:"));
				JTextField field2 = new JTextField(50);
				panel.add(field2);
				int value = JOptionPane.showConfirmDialog(frame, panel, "Enter text", JOptionPane.OK_CANCEL_OPTION);
				if (value == JOptionPane.OK_OPTION)
				{
				    // OK was pressed
				    String s1 = field1.getText();
				    String s2 = field2.getText();
				    int v1 = comboList1.getSelectedIndex()+1;
				    int v2 = comboList2.getSelectedIndex()+1;
				    int v3 = comboList3.getSelectedIndex()+1;
				    System.out.println(v1);
				    System.out.println(v2);
				    System.out.println(v3);
				    // handle them
				    String format = Functionality.Format(s1, s2, v1, v2, v3);
				    textArea.append(format);
				}
			}
		});

		JButton btnNewLine = new JButton("New Line");
		toolBar.add(btnNewLine);
		btnNewLine.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String newLine = Functionality.NewLine();
				textArea.append(newLine);
			}
		});

		//JButton btnLink = new JButton("Link");
		//toolBar.add(btnLink);

		JButton btnImage = new JButton("Image");
		toolBar.add(btnImage);
		btnImage.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				panel.add(new JLabel("Scale factor:"));
				JTextField field1 = new JTextField(10);
				panel.add(field1);
				panel.add(new JLabel("Image filename:"));
				JTextField field2 = new JTextField(10);
				panel.add(field2);
				int value = JOptionPane.showConfirmDialog(frame, panel, "Enter text", JOptionPane.OK_CANCEL_OPTION);
				if (value == JOptionPane.OK_OPTION)
				{
				    // OK was pressed
				    String txt = field1.getText();
				    String file = field2.getText();
				    String image = Functionality.Image(txt, file);
				    textArea.append(image);
				}
			}
		});

		JButton btnOrderedList = new JButton("Ordered List");
		toolBar.add(btnOrderedList);
		btnOrderedList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				String[] fontchoices = {"Times", "Helvetica", "Courrier"};
				String[] fontstyle = {"Regular", "Bold", "Italic", "Bold Italic"};
				String[] fontcolour = {"Black", "Blue", "Red", "Yellow"};
				//JList list1 = new JList(fontchoices);
				JComboBox comboList1 = new JComboBox(fontchoices); 
				comboList1.setSelectedIndex(1); 
				panel.add(comboList1);
				JComboBox comboList2 = new JComboBox(fontstyle); 
				comboList2.setSelectedIndex(1); 
				panel.add(comboList2);
				JComboBox comboList3 = new JComboBox(fontcolour); 
				comboList3.setSelectedIndex(1); 
				panel.add(comboList3);
				panel.add(new JLabel("Font Size:"));
				JTextField field1 = new JTextField(10);
				panel.add(field1);
				panel.add(new JLabel("Text:"));
				JTextField field2 = new JTextField(50);
				panel.add(field2);
				int value = JOptionPane.showConfirmDialog(frame, panel, "Enter text", JOptionPane.OK_CANCEL_OPTION);
				if (value == JOptionPane.OK_OPTION)
				{
				    // OK was pressed
				    String s1 = field1.getText();
				    String s2 = field2.getText();
				    int v1 = comboList1.getSelectedIndex()+1;
				    int v2 = comboList2.getSelectedIndex()+1;
				    int v3 = comboList3.getSelectedIndex()+1;
				    System.out.println(v1);
				    System.out.println(v2);
				    System.out.println(v3);
				    // handle them
				    String orderedList = Functionality.OrderedList(s1, s2, v1, v2, v3);
				    textArea.append(orderedList);
				}
			}
		});

		JButton btnUnorderedList = new JButton("Unordered List");
		toolBar.add(btnUnorderedList);
		btnUnorderedList.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JPanel panel = new JPanel();
				String[] fontchoices = {"Times", "Helvetica", "Courrier"};
				String[] fontstyle = {"Regular", "Bold", "Italic", "Bold Italic"};
				String[] fontcolour = {"Black", "Blue", "Red", "Yellow"};
				//JList list1 = new JList(fontchoices);
				JComboBox comboList1 = new JComboBox(fontchoices); 
				comboList1.setSelectedIndex(1); 
				panel.add(comboList1);
				JComboBox comboList2 = new JComboBox(fontstyle); 
				comboList2.setSelectedIndex(1); 
				panel.add(comboList2);
				JComboBox comboList3 = new JComboBox(fontcolour); 
				comboList3.setSelectedIndex(1); 
				panel.add(comboList3);
				panel.add(new JLabel("Font Size:"));
				JTextField field1 = new JTextField(10);
				panel.add(field1);
				panel.add(new JLabel("Text:"));
				JTextField field2 = new JTextField(50);
				panel.add(field2);
				int value = JOptionPane.showConfirmDialog(frame, panel, "Enter text", JOptionPane.OK_CANCEL_OPTION);
				if (value == JOptionPane.OK_OPTION)
				{
				    // OK was pressed
				    String s1 = field1.getText();
				    String s2 = field2.getText();
				    int v1 = comboList1.getSelectedIndex()+1;
				    int v2 = comboList2.getSelectedIndex()+1;
				    int v3 = comboList3.getSelectedIndex()+1;
				    System.out.println(v1);
				    System.out.println(v2);
				    System.out.println(v3);
				    // handle them
				    String unorderedList = Functionality.UnorderedList(s1, s2, v1, v2, v3);
				    textArea.append(unorderedList);
				}
			}
		});

	}
}
