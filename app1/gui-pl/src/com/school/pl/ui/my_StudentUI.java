package com.school.pl.ui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import com.school.pl.model.*;
import com.school.pl.model.exceptions.*;
import com.school.pl.pojo.*;
import javax.swing.event.*;

public class StudentUI extends JFrame
{

private JTable table;
private StudentModel studentModel;
private int height;
private int width;
private JScrollPane scrollPane;
private Container container;

public StudentUI()
{
super("Student Management");
ImageIcon imageIcon=new ImageIcon("16x16-icon-45597.png");
setIconImage(imageIcon.getImage());

container=this.getContentPane();

try{
studentModel=new StudentModel();
}catch(ModelException modelException)
{
JOptionPane.showMessageDialog(null,modelException.getMessage());
return;
}

table=new JTable(studentModel);
scrollPane=new JScrollPane(table,ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
this.container.setLayout(new BorderLayout());
this.container.add(scrollPane,BorderLayout.CENTER);

}


}
