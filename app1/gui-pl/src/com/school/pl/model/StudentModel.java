package com.school.pl.model;

import javax.swing.table.*;
import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.dto.*;
import com.school.datalayer.dao.*;
import com.school.datalayer.exceptions.*;
import java.util.*;
import com.school.pl.model.exceptions.*;
import com.school.pl.pojo.*; 

//itext pdf imports
import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class StudentModel extends AbstractTableModel
{
private StudentDAOInterface studentDAOInterface;
private List<Student> students; 
private String titles[]={"S.No.","Roll No.","Name","Gender"};

public StudentModel() throws ModelException
{

try
{
studentDAOInterface=new StudentDAO();
List<StudentDTOInterface> dtoStudents;
dtoStudents=studentDAOInterface.getAll();
students=new ArrayList<Student>();
Student student;
int rollNumber;
String name;
char g;
String gender;
for(StudentDTOInterface studentDTO:dtoStudents)
{
rollNumber=studentDTO.getRollNumber();
name=studentDTO.getName();
g=studentDTO.getGender();
if(g=='M') gender="Male";
else gender="Female";
student=new Student(rollNumber,name,gender);
students.add(student);
}
Collections.sort(students,new Comparator<Student>(){
public int compare(Student left,Student right)
{
return left.getName().compareToIgnoreCase(right.getName());
}
});

}catch(DLException dlException)
{
throw new ModelException(dlException.getMessage());
}
}

public int getColumnCount()
{
return 4;
}

public int getRowCount()
{
return students.size();
}

public String getColumnName(int index)
{
return titles[index];
}

public boolean isCellEditable(int rowIndex,int columnIndex)
{
return false;
}

public Object getValueAt(int rowIndex,int columnIndex)
{
if(columnIndex==0) return rowIndex+1;
Student student=this.students.get(rowIndex);
if(columnIndex==1) return student.getRollNumber();
if(columnIndex==2) return student.getName();
if(columnIndex==3) return student.getGender();
return null; // this line will never be executed
}

public Class getColumnClass(int columnIndex)
{
Class c=null; if(columnIndex==0 || columnIndex==1) c=Integer.class;
else c=String.class;
return c;
}

//1sept
public int searchByRollNumber(int rollNumber)
{
int index=0;
for(Student s:students)
{
if(s.getRollNumber()==rollNumber) return index;
index++;
}
return -1;
}


//27 aug
public int searchPartialByName(String partialName)
{
int index=0;
for(Student s:students)
{
if(s.getName().toUpperCase().startsWith(partialName.toUpperCase())) return index;
index++;
}
return -1;
}

public Student getStudentByIndex(int index)
{
if(index>=0 && index<this.students.size())
{
return this.students.get(index);
}
return null;
}

public int addStudent(Student student) throws ModelException
{
if(searchByRollNumber(student.getRollNumber())!=-1) 
{
throw new ModelException("Roll number exists");
}
StudentDTOInterface studentDTOInterface; 
studentDTOInterface=new StudentDTO(student.getRollNumber(),student.getName(),student.getGender().charAt(0));
try
{
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();
studentDAOInterface.add(studentDTOInterface);
int index=0;
for(Student s:students)
{
if(s.getName().compareToIgnoreCase(student.getName())>0) break;
index++;
}
students.add(index,student); 
this.fireTableDataChanged(); // Table that is related to the model
			// will be notified that model has changed,
			// Because of the notification, table will
			// update itself
return index;
}catch(DLException dlException)
{
throw new ModelException(dlException.getMessage());
}
}

public void deleteByRollNumber(int rollNumber) throws ModelException
{
int index=searchByRollNumber(rollNumber);
if(index==-1) 
{
throw new ModelException("Roll number does not exists");
}
try
{
StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO(); 
studentDAOInterface.removeByRollNumber(rollNumber);
students.remove(index);
this.fireTableDataChanged(); 
}catch(DLException dlException)
{
throw new ModelException(dlException.getMessage());
}
}

//mine pdf
public void exportToPDF(String path) throws ModelException
{
try
{
Document document = new Document();

PdfWriter.getInstance(document, new FileOutputStream(path));

document.open();

document.add(new Paragraph("Student Details"));

for(Student student : students)
{
document.add(new Paragraph("Roll No.: " + student.getRollNumber()));
document.add(new Paragraph("Name: " + student.getName()));
document.add(new Paragraph("Gender: " + student.getGender()));

document.add(new Paragraph(" "));
document.add(new Paragraph(" "));
}

document.close();
}
catch(Exception e)
{
throw new ModelException("Unable to export PDF: " + e.getMessage());
}
}

}
