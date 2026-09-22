package com.school.pl.ui;

import javax.swing.event.*;

import javax.swing.*;
import java.awt.*;

import java.awt.event.*;
import com.school.pl.pojo.*;

import java.io.*;

import com.school.pl.model.*;
import com.school.pl.model.exceptions.*;

public class StudentUI extends JFrame
{
//5sept
private int VIEW_MODE=1;
private int ADD_MODE=2;
private int EDIT_MODE=3;
private int DELETE_MODE=4;
private int mode;

//3 sept
// details view panel components
private JPanel detailsViewPanel;

private JLabel detailsViewPanelRollNumberCaptionLabel;
private JLabel detailsViewPanelRollNumberLabel;

private JLabel detailsViewPanelNameCaptionLabel;
private JLabel detailsViewPanelNameLabel;

private JLabel detailsViewPanelGenderCaptionLabel;
private JLabel detailsViewPanelGenderLabel;

//5sept
private JTextField detailsViewPanelRollNumberTextField;
private JTextField detailsViewPanelNameTextField;
private JRadioButton detailsViewPanelMaleRadioButton;
private JRadioButton detailsViewPanelFemaleRadioButton;
private ButtonGroup detailsViewPanelGenderButtonGroup;

private JButton detailsViewPanelExportToPDFButton;

private JButton detailsViewPanelAddButton;
private JButton detailsViewPanelEditButton;
private JButton detailsViewPanelDeleteButton;
private JButton detailsViewPanelSaveButton;
private JButton detailsViewPanelCancelButton;
//declare icons for them we have currently used text

private int searchBySelected;

//27aug A
private Color searchTextFieldDefaultColor;
private Color searchTextFieldErrorColor;

private JRadioButton searchByNameRadioButton;
private JRadioButton searchByRollNumberRadioButton;
private ButtonGroup searchByRadioButtonGroup;

private JLabel searchByLabel;
private JLabel searchLabel;
private JTextField searchTextField;
private JPanel searchPanel;

private JTable table;
private JScrollPane scrollPane;
private StudentModel studentModel;
private int width;
private int height;
private Container container;

public StudentUI()
{
super("Student Manager");
ImageIcon imageIcon=new ImageIcon("16x16-icon-45597.png");
container=this.getContentPane();
setIconImage(imageIcon.getImage());

try
{
studentModel = new StudentModel();
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(null,modelException.getMessage());
return;
}

table = new JTable(studentModel);
scrollPane=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

this.container.setLayout(new BorderLayout());

this.searchByLabel=new JLabel("Search By");

this.searchBySelected=1;

this.searchByNameRadioButton=new JRadioButton("Name",true);
this.searchByRollNumberRadioButton=new JRadioButton("Roll No",false);

this.searchByRadioButtonGroup=new ButtonGroup();
this.searchByRadioButtonGroup.add(this.searchByNameRadioButton);
this.searchByRadioButtonGroup.add(this.searchByRollNumberRadioButton);

this.searchLabel=new JLabel("Search");
this.searchTextField=new JTextField();

//27aug B
this.searchTextFieldDefaultColor=this.searchTextField.getForeground();
this.searchTextFieldErrorColor=Color.red;

this.searchPanel=new JPanel();
this.searchPanel.setLayout(null);
Font searchPanelFont = new Font("Times New Roman", Font.PLAIN, 24);

this.searchPanel.setPreferredSize(new Dimension(500,70));
this.searchByLabel.setFont(searchPanelFont);
this.searchByLabel.setBounds(20,30,100,30);
this.searchPanel.add(this.searchByLabel);

this.searchByNameRadioButton.setFont(searchPanelFont); this.searchByNameRadioButton.setBounds(130,30,100,30); this.searchPanel.add(this.searchByNameRadioButton); this.searchByRollNumberRadioButton.setFont(searchPanelFont); this.searchByRollNumberRadioButton.setBounds(225,30,200,30); this.searchPanel.add(this.searchByRollNumberRadioButton);
this.searchLabel.setFont(searchPanelFont);
this.searchLabel.setBounds(425,30,90,30);
this.searchPanel.add(this.searchLabel);
this.searchTextField.setFont(searchPanelFont);
this.searchTextField.setBounds(515,30,400,30);
this.searchPanel.add(this.searchTextField); 


														 //3sept

Font tableFont = new Font("Times New Roman", Font.PLAIN, 24);
table.setFont(tableFont);
table.setRowHeight(35);
table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

table.getColumnModel().getColumn(0).setPreferredWidth(50);
table.getColumnModel().getColumn(1).setPreferredWidth(50);
table.getColumnModel().getColumn(2).setPreferredWidth(300);
table.getColumnModel().getColumn(3).setPreferredWidth(50);

table.getTableHeader().setResizingAllowed(false);
table.getTableHeader().setReorderingAllowed(false);

//3sept
this.detailsViewPanel=new JPanel();
this.detailsViewPanel.setLayout(null);
this.detailsViewPanel.setPreferredSize(new Dimension(500,180));

//These are the fixed labels: Roll No. Name Gender

this.detailsViewPanelRollNumberCaptionLabel=new JLabel("Roll No.");

this.detailsViewPanelNameCaptionLabel=new JLabel("Name");

this.detailsViewPanelGenderCaptionLabel=new JLabel("Gender");

//labels that will contain student data
this.detailsViewPanelRollNumberLabel=new JLabel("");

this.detailsViewPanelNameLabel=new JLabel("");

this.detailsViewPanelGenderLabel=new JLabel("");

//5sept
this.detailsViewPanelRollNumberTextField=new JTextField(); this.detailsViewPanelNameTextField=new JTextField(); this.detailsViewPanelMaleRadioButton=new JRadioButton("Male",true); this.detailsViewPanelFemaleRadioButton=new JRadioButton("Female"); this.detailsViewPanelGenderButtonGroup=new ButtonGroup(); this.detailsViewPanelGenderButtonGroup.add(this.detailsViewPanelMaleRadioButton); this.detailsViewPanelGenderButtonGroup.add(this.detailsViewPanelFemaleRadioButton);

this.detailsViewPanelAddButton=new JButton("A");
this.detailsViewPanelEditButton=new JButton("E");
this.detailsViewPanelDeleteButton=new JButton("D");
this.detailsViewPanelSaveButton=new JButton("S"); this.detailsViewPanelCancelButton=new JButton("C"); this.detailsViewPanelExportToPDFButton=new JButton("P");

Font detailsViewPanelFont=new Font("Times New Roman",Font.PLAIN,24);

this.detailsViewPanelRollNumberCaptionLabel.setFont(detailsViewPanelFont);
this.detailsViewPanelNameCaptionLabel.setFont(detailsViewPanelFont);
this.detailsViewPanelGenderCaptionLabel.setFont(detailsViewPanelFont);

this.detailsViewPanelRollNumberTextField.setFont(detailsViewPanelFont);
this.detailsViewPanelNameTextField.setFont(detailsViewPanelFont);
this.detailsViewPanelMaleRadioButton.setFont(detailsViewPanelFont);
this.detailsViewPanelFemaleRadioButton.setFont(detailsViewPanelFont);

this.detailsViewPanelRollNumberLabel.setFont(detailsViewPanelFont);
this.detailsViewPanelNameLabel.setFont(detailsViewPanelFont);
this.detailsViewPanelGenderLabel.setFont(detailsViewPanelFont);

this.detailsViewPanelRollNumberCaptionLabel.setBounds(20,20,150,30);
this.detailsViewPanelRollNumberLabel.setBounds(180,20,150,30);

//5sept
this.detailsViewPanelRollNumberTextField.setBounds(180,20,150,30);
this.detailsViewPanelNameTextField.setBounds(180,55,450,30);
this.detailsViewPanelMaleRadioButton.setBounds(180,90,150,30);
this.detailsViewPanelFemaleRadioButton.setBounds(350,90,150,30);

this.detailsViewPanelAddButton.setBounds(450,120,50,50);
this.detailsViewPanelEditButton.setBounds(520,120,50,50);
this.detailsViewPanelDeleteButton.setBounds(590,120,50,50);
this.detailsViewPanelSaveButton.setBounds(660,120,50,50);
this.detailsViewPanelCancelButton.setBounds(730,120,50,50);

this.detailsViewPanelExportToPDFButton.setBounds(800,120,50,50);

setViewMode();

this.detailsViewPanelNameCaptionLabel.setBounds(20,55,150,30);
this.detailsViewPanelNameLabel.setBounds(180,55,450,30);

this.detailsViewPanelGenderCaptionLabel.setBounds(20,90,150,30);
this.detailsViewPanelGenderLabel.setBounds(180,90,150,30);

this.detailsViewPanel.add(this.detailsViewPanelRollNumberCaptionLabel);
this.detailsViewPanel.add(this.detailsViewPanelRollNumberLabel);
this.detailsViewPanel.add(this.detailsViewPanelNameCaptionLabel);
this.detailsViewPanel.add(this.detailsViewPanelNameLabel);
this.detailsViewPanel.add(this.detailsViewPanelGenderCaptionLabel);
this.detailsViewPanel.add(this.detailsViewPanelGenderLabel);

this.detailsViewPanel.add(this.detailsViewPanelRollNumberTextField);
this.detailsViewPanel.add(this.detailsViewPanelNameTextField);
this.detailsViewPanel.add(this.detailsViewPanelMaleRadioButton);
this.detailsViewPanel.add(this.detailsViewPanelFemaleRadioButton);
this.detailsViewPanel.add(this.detailsViewPanelAddButton);
this.detailsViewPanel.add(this.detailsViewPanelEditButton);
this.detailsViewPanel.add(this.detailsViewPanelDeleteButton);
this.detailsViewPanel.add(this.detailsViewPanelSaveButton);
this.detailsViewPanel.add(this.detailsViewPanelCancelButton);
this.detailsViewPanel.add(this.detailsViewPanelExportToPDFButton);

this.container.add(this.searchPanel,BorderLayout.NORTH);
this.container.add(scrollPane,BorderLayout.CENTER);
this.container.add(this.detailsViewPanel,BorderLayout.SOUTH); //3sept
this.addEventHandlers();


this.width=1200;
this.height=600;
setSize(this.width,this.height);

Dimension dimension=Toolkit.getDefaultToolkit().getScreenSize();
setLocation(dimension.width/2-width/2,dimension.height/2-this.height/2);
setVisible(true);
}

//8 sept 
private void clearDetailsViewPanelInputComponent()
{
this.detailsViewPanelRollNumberTextField.setText("");
this.detailsViewPanelNameTextField.setText("");
this.detailsViewPanelMaleRadioButton.setSelected(true);
}

//5sept
private void setViewMode()
{
this.mode=VIEW_MODE; this.searchByRollNumberRadioButton.setEnabled(true);
this.searchByNameRadioButton.setEnabled(true);
this.searchTextField.setEnabled(true);
this.table.setEnabled(true);

this.detailsViewPanelRollNumberTextField.setVisible(false);
this.detailsViewPanelNameTextField.setVisible(false);
this.detailsViewPanelMaleRadioButton.setVisible(false);
this.detailsViewPanelFemaleRadioButton.setVisible(false);
this.detailsViewPanelRollNumberLabel.setVisible(true);
this.detailsViewPanelNameLabel.setVisible(true);
this.detailsViewPanelGenderLabel.setVisible(true);

this.detailsViewPanelAddButton.setEnabled(true);
this.detailsViewPanelEditButton.setEnabled(true);
this.detailsViewPanelDeleteButton.setEnabled(true);
this.detailsViewPanelSaveButton.setEnabled(false);
this.detailsViewPanelCancelButton.setEnabled(false);
}

private void setAddMode()
{
this.mode=ADD_MODE; this.searchByRollNumberRadioButton.setEnabled(false);
this.searchByNameRadioButton.setEnabled(false);
this.searchTextField.setEnabled(false);
this.table.setEnabled(false);

clearDetailsViewPanelInputComponent();
this.detailsViewPanelRollNumberLabel.setVisible(false);
this.detailsViewPanelNameLabel.setVisible(false);
this.detailsViewPanelGenderLabel.setVisible(false);
this.detailsViewPanelRollNumberTextField.setVisible(true);
this.detailsViewPanelNameTextField.setVisible(true);
this.detailsViewPanelMaleRadioButton.setVisible(true);
this.detailsViewPanelFemaleRadioButton.setVisible(true);

this.detailsViewPanelAddButton.setEnabled(false);
this.detailsViewPanelEditButton.setEnabled(false);
this.detailsViewPanelDeleteButton.setEnabled(false);
this.detailsViewPanelSaveButton.setEnabled(true);
this.detailsViewPanelCancelButton.setEnabled(true);
}

private void setEditMode()
{
}
private void setDeleteMode()
{
}


//3sept
private void addEventHandlers()
{
//12sept
this.detailsViewPanelExportToPDFButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
JFileChooser jfc=new JFileChooser();
jfc.addChoosableFileFilter(new javax.swing.filechooser.FileFilter(){
public String getDescription()
{
return "pdf files (*.pdf)";
}
public boolean accept(File file)
{
String fileName=file.getName().toUpperCase();
return fileName.endsWith("PDF");
}
});
int selectedOption=jfc.showSaveDialog(null);
if(selectedOption==jfc.APPROVE_OPTION)
{
// get the path
// exportToPDF(path);

File file=jfc.getSelectedFile();
String path=file.getAbsolutePath();
if(!path.toUpperCase().endsWith(".PDF"))
{
path+=".pdf";
}
try
{
StudentUI.this.studentModel.exportToPDF(path);
JOptionPane.showMessageDialog(StudentUI.this,"PDF exported successfully");
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(StudentUI.this,modelException.getMessage());
}

}
else
{
System.out.println("don't save");
}
}
});


//10sept
this.detailsViewPanelDeleteButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
int selectedRowIndex=table.getSelectedRow();
if(selectedRowIndex==-1)
{
JOptionPane.showMessageDialog(StudentUI.this,"Select student to delete");
return;
}
Student student; student=StudentUI.this.studentModel.getStudentByIndex(selectedRowIndex);
int userSelection; userSelection=JOptionPane.showConfirmDialog(StudentUI.this,"Delete [Roll No.:"+student.getRollNumber()+", Name : "+student.getName()+"] ?","Delete confirmation",JOptionPane.YES_NO_OPTION);
if(userSelection==JOptionPane.YES_OPTION)
{
// try to complete
try
{
StudentUI.this.studentModel.deleteByRollNumber(student.getRollNumber());
JOptionPane.showMessageDialog(StudentUI.this,"Student deleted");
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(StudentUI.this,modelException.getMessage());
}
}
else
{
JOptionPane.showMessageDialog(StudentUI.this,"Student not deleted");
}
}
});

//8sept
this.detailsViewPanelSaveButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
if(StudentUI.this.mode==ADD_MODE)
{
String rollNumberString=StudentUI.this.detailsViewPanelRollNumberTextField.getText().trim();
String name=StudentUI.this.detailsViewPanelNameTextField.getText().trim();
String gender;
if(StudentUI.this.detailsViewPanelMaleRadioButton.isSelected())
{
gender="Male";
}
else
{
gender="Female";
}
if(rollNumberString.length()==0)
{
JOptionPane.showMessageDialog(StudentUI.this,"Roll number required");
StudentUI.this.detailsViewPanelRollNumberTextField.requestFocus();
return;
}
if(name.length()==0)
{
JOptionPane.showMessageDialog(StudentUI.this,"Name required");
StudentUI.this.detailsViewPanelNameTextField.requestFocus();
return;
}
int rollNumber=Integer.parseInt(rollNumberString);
Student student=new Student(rollNumber,name,gender);
try
{
//10sept change
int index=StudentUI.this.studentModel.addStudent(student);
StudentUI.this.table.setRowSelectionInterval(index,index);
StudentUI.this.table.scrollRectToVisible(StudentUI.this.table.getCellRect(index,0,true));
JOptionPane.showMessageDialog(StudentUI.this,"Student added");
StudentUI.this.setViewMode();
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(StudentUI.this,modelException.getMessage());
}
}
if(StudentUI.this.mode==EDIT_MODE)
{
}
}
});

this.detailsViewPanelCancelButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
StudentUI.this.setViewMode();
}
});

this.detailsViewPanelAddButton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent ev)
{
StudentUI.this.setAddMode();
}
});

this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
public void valueChanged(ListSelectionEvent ev)
{
int selectedRowIndex=table.getSelectedRow();
Student student;
student=StudentUI.this.studentModel.getStudentByIndex(selectedRowIndex);

if(student==null)
{
StudentUI.this.detailsViewPanelRollNumberLabel.setText("");
StudentUI.this.detailsViewPanelNameLabel.setText("");
StudentUI.this.detailsViewPanelGenderLabel.setText("");
}

else
{
StudentUI.this.detailsViewPanelRollNumberLabel.setText(String.valueOf(student.getRollNumber()));
StudentUI.this.detailsViewPanelNameLabel.setText(student.getName());
StudentUI.this.detailsViewPanelGenderLabel.setText(student.getGender());
}

}
});

//1 sept
this.searchByRollNumberRadioButton.addActionListener(new ActionListener(){

public void actionPerformed(ActionEvent ev)
{
StudentUI.this.searchBySelected=2;
StudentUI.this.searchTextField.setForeground(StudentUI.this.searchTextFieldDefaultColor);
StudentUI.this.searchTextField.setText("");
}

});

this.searchByNameRadioButton.addActionListener(new ActionListener(){

public void actionPerformed(ActionEvent ev)
{
StudentUI.this.searchBySelected=1;
StudentUI.this.searchTextField.setForeground(StudentUI.this.searchTextFieldDefaultColor);
StudentUI.this.searchTextField.setText("");
}

});

this.searchTextField.addKeyListener(new KeyAdapter(){

public void keyTyped(KeyEvent ev)
{
if(StudentUI.this.searchBySelected==2)
{
int keyChar=ev.getKeyChar();
if(keyChar==10) StudentUI.this.performSearch();
if(keyChar<48 || keyChar>57)
{
ev.consume();
}
}
}

});

this.detailsViewPanelRollNumberTextField.addKeyListener(new KeyAdapter(){
public void keyTyped(KeyEvent ev)
{
int keyChar=ev.getKeyChar();
if(keyChar<48 || keyChar>57)
{
ev.consume();
}
}
});

//27aug D
this.searchTextField.getDocument().addDocumentListener(new DocumentListener()
{
public void insertUpdate(DocumentEvent ev)
{
if(StudentUI.this.searchBySelected==1) performSearch();
}
public void removeUpdate(DocumentEvent ev)
{
if(StudentUI.this.searchBySelected==1) performSearch();
}
public void changedUpdate(DocumentEvent ev)
{
if(StudentUI.this.searchBySelected==1) performSearch();
}

});


}

//27aug C
public void performSearch()
{

//1 sept
if(searchByRollNumberRadioButton.isSelected())
{
this.searchTextField.setForeground(searchTextFieldDefaultColor);
String rollNumberString=searchTextField.getText();
if(rollNumberString.length()==0) return;

int rollNumberToSearch=0;

try
{
rollNumberToSearch=Integer.parseInt(rollNumberString);
int index=this.studentModel.searchByRollNumber(rollNumberToSearch);
if(index==-1)
{
this.searchTextField.setForeground(this.searchTextFieldErrorColor);
}

else
{
this.searchTextField.setForeground(this.searchTextFieldDefaultColor);
this.table.setRowSelectionInterval(index,index);
this.table.scrollRectToVisible(this.table.getCellRect(index,0,true));
}

}catch(ArithmeticException arithmeticException)
{
this.searchTextField.setForeground(searchTextFieldErrorColor);
}

}

else if(searchByNameRadioButton.isSelected())
{
String nameToSearch=searchTextField.getText().trim();
if(nameToSearch.length()==0) return;
int index=this.studentModel.searchPartialByName(nameToSearch);

if(index==-1)
{
this.searchTextField.setForeground(this.searchTextFieldErrorColor);
}

else
{
this.searchTextField.setForeground(this.searchTextFieldDefaultColor);
this.table.setRowSelectionInterval(index,index);
this.table.scrollRectToVisible(this.table.getCellRect(index,0,true));
}

}
}

}
