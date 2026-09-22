package com.school.pl;

import java.util.*;
import java.io.*;
import com.school.datalayer.exceptions.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.dto.*;
import com.school.datalayer.dao.*;
import com.io.stdin.*;

public class StudentUI
{
public void addStudent() throws IOException
{
System.out.println("--------------------------------------------------");
System.out.println("Student (Add Module)");
System.out.println("--------------------------------------------------");
int rollNumber;
rollNumber = Keyboard.readInt("Enter roll number: ");
if(rollNumber <= 0)
{
Keyboard.wait("Invalid roll number");
return;
}
String name;
name = Keyboard.readString("Enter name : ");
name = name.trim();
if(name.length() == 0)
{
Keyboard.wait("Name required");
return;
}
if(name.length() > 50)
{
Keyboard.wait("Name cannot exceed 50 characters");
return;
}
System.out.println("Gender");
System.out.println("1. Male");
System.out.println("2. Female");
int genderChoice = Keyboard.readInt("Select gender (1/2) : ");
if(genderChoice < 1 || genderChoice > 2)
{
Keyboard.wait("Invalid gender choice");
return;
}
char gender = 'M';
if(genderChoice == 2) gender = 'F';

char save;
while(true)
{
save = Keyboard.readChar("Save (Y/N) : ");
if(save != 'y' && save != 'Y' && save != 'n' && save != 'N')
{
Keyboard.wait("Invalid input");
continue;
}
break;
}
if(save == 'n' || save == 'N')
{
Keyboard.wait("Student not added");
return;
}
try
{
StudentDTOInterface studentDTOInterface;
studentDTOInterface = new StudentDTO(rollNumber, name, gender);
StudentDAOInterface studentDAOInterface;
studentDAOInterface = new StudentDAO();
studentDAOInterface.add(studentDTOInterface);
Keyboard.wait("Student added");
}
catch(DLException dlException)
{
Keyboard.wait("Student not added, " + dlException.getMessage());
}
}

public void updateStudent() throws IOException
{
System.out.println("--------------------------------------------------");
System.out.println("Student (Update Module)");
System.out.println("--------------------------------------------------");
int rollNumber;
rollNumber = Keyboard.readInt("Enter roll number: ");
if(rollNumber <= 0)
{
Keyboard.wait("Invalid roll number");
return;
}
StudentDTOInterface studentDTOInterface;
StudentDAOInterface studentDAOInterface;
studentDAOInterface = new StudentDAO();
try
{
studentDTOInterface = studentDAOInterface.getByRollNumber(rollNumber);
System.out.println("Name: " + studentDTOInterface.getName());
System.out.println("Gender : " + (studentDTOInterface.getGender() == 'M' ? "Male" : "Female"));
}
catch(DLException dlException)
{
Keyboard.wait(dlException.getMessage());
return;
}

char edit;
edit = Keyboard.readChar("Edit (Y/N) : ");
if(edit != 'y' && edit != 'Y')
{
Keyboard.wait("Student not updated");
return;
}

String name;
name = Keyboard.readString("Enter name : ");
name = name.trim();
if(name.length() == 0)
{
Keyboard.wait("Name required");
return;
}
if(name.length() > 50)
{
Keyboard.wait("Name cannot exceed 50 characters");
return;
}

System.out.println("1. Male");
System.out.println("2. Female");
int genderChoice = Keyboard.readInt("Select gender (1/2) : ");
if(genderChoice < 1 || genderChoice > 2)
{
Keyboard.wait("Invalid gender choice");
return;
}
char gender = 'M';
if(genderChoice == 2) gender = 'F';

char update;
while(true)
{
update = Keyboard.readChar("Save (Y/N) : ");
if(update != 'y' && update != 'Y' && update != 'n' && update != 'N')
{
Keyboard.wait("Invalid input");
continue;
}
break;
}
if(update == 'n' || update == 'N')
{
Keyboard.wait("Student not updated");
return;
}
try
{
studentDTOInterface = new StudentDTO(rollNumber, name, gender);
studentDAOInterface.update(studentDTOInterface);
Keyboard.wait("Student updated");
}
catch(DLException dlException)
{
Keyboard.wait("Student not updated, " + dlException.getMessage());
}
}

public void removeStudent() throws IOException
{
System.out.println("--------------------------------------------------");
System.out.println("Student (Delete Module)");
System.out.println("--------------------------------------------------");
int rollNumber;
rollNumber = Keyboard.readInt("Enter roll number: ");
if(rollNumber <= 0)
{
Keyboard.wait("Invalid roll number");
return;
}
StudentDTOInterface studentDTOInterface;
StudentDAOInterface studentDAOInterface;
studentDAOInterface = new StudentDAO();
try
{
studentDTOInterface = studentDAOInterface.getByRollNumber(rollNumber);
System.out.println("Name: " + studentDTOInterface.getName());
System.out.println("Gender : " + (studentDTOInterface.getGender() == 'M' ? "Male" : "Female"));
}
catch(DLException dlException)
{
Keyboard.wait(dlException.getMessage());
return;
}

char delete;
delete = Keyboard.readChar("Remove (Y/N) : ");
if(delete != 'y' && delete != 'Y')
{
Keyboard.wait("Student not removed");
return;
}
try
{
studentDAOInterface.removeByRollNumber(rollNumber);
Keyboard.wait("Student removed");
}
catch(DLException dlException)
{
Keyboard.wait("Student not removed, " + dlException.getMessage());
}
}

public void searchStudent() throws IOException
{
System.out.println("--------------------------------------------------");
System.out.println("Student (Search Module)");
System.out.println("--------------------------------------------------");
int rollNumber;
rollNumber = Keyboard.readInt("Enter roll number: ");
if(rollNumber <= 0)
{
Keyboard.wait("Invalid roll number");
return;
}
StudentDTOInterface studentDTOInterface;
StudentDAOInterface studentDAOInterface;
studentDAOInterface = new StudentDAO();
try
{
studentDTOInterface = studentDAOInterface.getByRollNumber(rollNumber);
System.out.println("Name: " + studentDTOInterface.getName());
System.out.println("Gender : " + (studentDTOInterface.getGender() == 'M' ? "Male" : "Female"));
Keyboard.wait(" ");
}
catch(DLException dlException)
{
Keyboard.wait(dlException.getMessage());
return;
}
}

public void displayList() throws IOException
{
StudentDAOInterface studentDAOInterface;
studentDAOInterface = new StudentDAO();
List<StudentDTOInterface> students = null;
try
{
students = studentDAOInterface.getAll();
}
catch(DLException dlException)
{
Keyboard.wait(dlException.getMessage());
return;
}

int pageSize = 3;
boolean header = true;
int sno = 0;

for(StudentDTOInterface student : students)
{
if(header)
{
System.out.println("--------------------------------------------------");
System.out.println("Student (List Module)");
System.out.println("--------------------------------------------------");
System.out.println("S.No. Roll No. Name                               Gender");
System.out.println("--------------------------------------------------");
header = false;
}
sno++;
System.out.printf("%5d %10d %50s %s\n", sno, student.getRollNumber(), student.getName(), student.getGender() == 'M' ? "Male" : "Female");
if(sno % pageSize == 0 || sno == students.size())
{
System.out.println("--------------------------------------------------");
Keyboard.wait(" ");
header = true;
}
}
}
}