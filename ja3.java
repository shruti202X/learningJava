import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.plaf.ColorUIResource;
import javax.swing.plaf.DimensionUIResource;
import java.io.*;

public class TextEditor {

    String copiedText = new String();

    public static void main(String[] args) {
        
        //Creating the main frame with the title of Text Editor
        JFrame mainFrame = new JFrame("Text Editor");

        //add layout to mainframe
        mainFrame.setLayout(new BorderLayout());

        //Creating the menu bar
        JMenuBar menuBar = new JMenuBar();
        
        //Attach the menu bar to the mainFrame
        mainFrame.setJMenuBar(menuBar);
        
        //Create the menu's
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenu review = new JMenu("Review");
        JMenu help = new JMenu("Help");

        //Make all the JMenu items to be of same preferred size
        //60 in width and 20 in height
        file.setPreferredSize(new Dimension(60, 20));
        edit.setPreferredSize(new Dimension(60, 20));
        review.setPreferredSize(new Dimension(60, 20));
        help.setPreferredSize(new Dimension(60, 20));

        //Add the menu's to the menubar
        menuBar.add(file);
        menuBar.add(edit);
        menuBar.add(review);
        menuBar.add(help);

        //Add menu items for file menu
        JMenuItem newFileMenuItem = new JMenuItem("New");
        JMenuItem openFileMenuItem = new JMenuItem("Open");
        JMenuItem saveFileMenuItem = new JMenuItem("Save");
        JMenuItem saveAsFileMenuItem = new JMenuItem("Save As");
        file.add(newFileMenuItem);
        file.add(openFileMenuItem);
        file.add(saveFileMenuItem);
        file.add(saveAsFileMenuItem);
        
        //Add menu items for edit menu
        JMenuItem cut, copy, paste, cWords, cChars;
        JMenu count;
        edit.add(cut = new JMenuItem("Cut"));
        edit.add(copy = new JMenuItem("Copy"));
        edit.add(count = new JMenu("Count"));
        count.add(cWords = new JMenuItem("Count Words"));
        count.add(cChars = new JMenuItem("Count Characters"));

        //Set shortcots for the menu items
        newFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        openFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        saveFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        saveAsFileMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));

        //Create the status bar
        JPanel statusBar = new JPanel();
        statusBar.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JLabel wcLbl = new JLabel("0");
        JLabel ccLbl = new JLabel("0");
        statusBar.add(new JLabel("Word Count:"));
        statusBar.add(wcLbl);
        statusBar.add(new JLabel("    "));
        statusBar.add(new JLabel("Character Count:"));
        statusBar.add(ccLbl);

        //Making the main working space of the text editor
        JPanel workingJPanel = new JPanel(new GridLayout(1, 2, 5, 2));
        JPanel editorJPanel = new JPanel();
        JPanel sketchJPanel = new JPanel();
        workingJPanel.add(editorJPanel);
        workingJPanel.add(sketchJPanel);

        //Developing the editorJPanel
        editorJPanel.setLayout(new BoxLayout(editorJPanel, BoxLayout.PAGE_AXIS));

        //Editing buttons
        JPanel editingTools = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JButton bold = new JButton("B");
        JButton italics = new JButton("I");
        JButton underline = new JButton("U");
        JButton strikethrough = new JButton("S");
        editingTools.add(bold);
        editingTools.add(italics);
        editingTools.add(underline);
        editingTools.add(strikethrough);

        //alignment buttons
        JPanel alignmentTools = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        JButton left = new JButton("left");
        JButton center = new JButton("center");
        JButton right = new JButton("right");
        JButton uniform = new JButton("uniform");
        alignmentTools.add(left);
        alignmentTools.add(center);
        alignmentTools.add(right);
        alignmentTools.add(uniform);

        //JComboBox to select font of editor text
        JComboBox fontJComboBox = new JComboBox();
        fontJComboBox.addItem("Ariel");
        fontJComboBox.addItem("Calibri");
        fontJComboBox.addItem("Comic Sans");

        //JComboBox to select size of editor text
        JComboBox sizeJComboBox = new JComboBox();
        for(int i=5; i<=40; i+=5){
            sizeJComboBox.addItem(i);
        }

        //Positioning all the top row elements
        JPanel topRow = new JPanel(new FlowLayout(FlowLayout.LEFT,0,0));
        topRow.add(editingTools);
        topRow.add(new JLabel("  "));
        topRow.add(alignmentTools);
        topRow.add(new JLabel("    "));
        topRow.add(fontJComboBox);
        topRow.add(sizeJComboBox);
        editorJPanel.add(topRow);

        //Place find and replace fields
        JPanel findReplacePanel = new JPanel(new GridLayout(5,1));
        //find
        findReplacePanel.add(new JLabel("    Find", JLabel.LEFT));
        JTextField findTextField = new JTextField();
        findTextField.setMinimumSize(topRow.getSize());
        findReplacePanel.add(findTextField);
        //replace
        findReplacePanel.add(new JLabel("    Replace", JLabel.LEFT));
        JTextField replaceTextField = new JTextField();
        replaceTextField.setMinimumSize(topRow.getSize());
        findReplacePanel.add(replaceTextField);
        //find and replace buttons
        JButton findAllBtn, findNxtBtn, replaceBtn, replaceAllBtn;
        JPanel findReplaceButtonsPanel = new JPanel();
        findReplaceButtonsPanel.add(findAllBtn = new JButton("Find All"));
        findReplaceButtonsPanel.add(findNxtBtn = new JButton("Find Next"));
        findReplaceButtonsPanel.add(replaceBtn = new JButton("Replace"));
        findReplaceButtonsPanel.add(replaceAllBtn = new JButton("Replace All"));
        findReplacePanel.add(findReplaceButtonsPanel);

        editorJPanel.add(findReplacePanel);

        //Adding the text area
        JTextArea editorTextArea = new JTextArea();
        JScrollBar scroll = new JScrollBar();
        scroll.setSize(editorTextArea.getSize());
        editorTextArea.add(scroll);
        editorJPanel.add(editorTextArea);

        //Increasing word and character count
        editorTextArea.addKeyListener(new KeyAdapter(){
            public void keyReleased(KeyEvent e){
                
            }
        });

        //Developing the sketchJPanel
        sketchJPanel.setLayout(new BoxLayout(sketchJPanel, BoxLayout.PAGE_AXIS));
        sketchJPanel.add(new JLabel("SketchPad"));

        //Adding buttons for shapes to the sketxh JPanel
        JPanel sketchButtonPanel = new JPanel();
        sketchButtonPanel.setLayout(new BoxLayout(sketchButtonPanel, BoxLayout.LINE_AXIS));
        JButton rectBtn, ovalBtn, lineBtn, triangleBtn, pentagonBtn, ClearBtn;
        sketchButtonPanel.add(rectBtn = new JButton("Rectangle"));
        sketchButtonPanel.add(new JButton("Oval"));
        sketchButtonPanel.add(new JButton("Line"));
        sketchButtonPanel.add(new JButton("Triangle"));
        sketchButtonPanel.add(new JButton("Pentagon"));
        sketchButtonPanel.add(new JLabel("  "));
        sketchButtonPanel.add(new JButton("CLEAR"));
        sketchJPanel.add(sketchButtonPanel);
        
        //Adding the main draw area to the sketchJPanel
        JPanel drawJPanel = new JPanel();
        drawJPanel.setBackground(Color.GRAY);
        sketchJPanel.add(drawJPanel);

        //Adding workingJPanel to the mainFrame
        mainFrame.add(workingJPanel, BorderLayout.CENTER);

        //Add the status bar on mainFrame
        mainFrame.add(statusBar, BorderLayout.SOUTH);

        //Set mainFrame size
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        mainFrame.setSize((int)(screenSize.getWidth()*0.8), (int)(screenSize.getHeight()*0.8));
        //mainFrame.pack();

        //Set the default close operation
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Make the frame visible for user
        mainFrame.setVisible(true);

        //assign action to 'new' menu item of file menu
        newFileMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creating new file");
            };
        });

        //assign action to 'open' menu item of file menu
        openFileMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFileChooser openFile = new JFileChooser();
                int res = openFile.showOpenDialog(mainFrame);
                if (res == JFileChooser.APPROVE_OPTION) {
                    File file = openFile.getSelectedFile();
                    try{
                        FileReader rd = new FileReader(file);
                        BufferedReader br = new BufferedReader(rd);
                        editorTextArea.read(br, null);
                        br.close();
                        editorTextArea.requestFocus();
                    }catch(Exception ex){}
                }
            };
        });

        //assign action to 'saveAs' menu item of file menu
        saveAsFileMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                JFileChooser saveAsFile = new JFileChooser();
                int res = saveAsFile.showSaveDialog(mainFrame);
                if(res == JFileChooser.APPROVE_OPTION) {
                    File myFile = saveAsFile.getSelectedFile();
                    try{
                        FileWriter wr = new FileWriter(myFile);
                        BufferedWriter bw = new BufferedWriter(wr);
                        editorTextArea.write(bw);
                        bw.close();
                        editorTextArea.requestFocus();
                        currFile = myFile;
                    }catch(Exception ex){}
                }
            }
        });

        //assign action to 'save' menu item of file menu
        saveFileMenuItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(currFile!=null){
                    try{
                        FileWriter wr = new FileWriter(currFile);
                        BufferedWriter bw = new BufferedWriter(wr);
                        editorTextArea.write(bw);
                        bw.close();
                        editorTextArea.requestFocus();
                    }catch(Exception ex){}
                }else{
                    JFileChooser saveAsFile = new JFileChooser();
                    int res = saveAsFile.showSaveDialog(mainFrame);
                    if(res == JFileChooser.APPROVE_OPTION) {
                        File myFile = saveAsFile.getSelectedFile();
                        try{
                            FileWriter wr = new FileWriter(myFile);
                            BufferedWriter bw = new BufferedWriter(wr);
                            editorTextArea.write(bw);
                            bw.close();
                            editorTextArea.requestFocus();
                            currFile = myFile;
                        }catch(Exception ex){}
                    }
                }
            }
        });
    }

    static File currFile  = null; 
}