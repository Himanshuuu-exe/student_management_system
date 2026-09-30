package com.school.datalayer.dao;

import java.sql.*;
import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.dto.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.exceptions.*;

import java.util.*;
import java.io.*;

public class StudentDAO implements StudentDAOInterface
{

public void add(StudentDTOInterface student) throws DLException
{
if(student==null) throw new DLException("Student required to add");

int rollNumber=student.getRollNumber();
String name=student.getName();
char gender=student.getGender(); 

if(rollNumber<=0) throw new DLException("Invalid roll number, cannot add"); 
if(name==null) throw new DLException("Name required, cannot add");

name=name.trim(); 

if(name.length()==0) throw new DLException("Name required, cannot add"); 
if(name.length()>50) throw new DLException("Name cannot be of more than 50 characters, cannot add"); if("MmFf".indexOf(gender)==-1) throw new DLException("Invalid gender, cannot add");

if(gender=='m') gender='M';
if(gender=='f') gender='F';

try
{
Connection connection;
connection=DBConnection.connect();
PreparedStatement preparedStatement; preparedStatement=connection.prepareStatement("select gender from student where roll_number=?");
preparedStatement.setInt(1,rollNumber);
ResultSet resultSet;
resultSet=preparedStatement.executeQuery();
if(resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close(); throw new DLException(rollNumber+" exists, cannot add");
}
resultSet.close();
preparedStatement.close(); preparedStatement=connection.prepareStatement("insert into student values(?,?,?)");
preparedStatement.setInt(1,rollNumber);
preparedStatement.setString(2,name); preparedStatement.setString(3,String.valueOf(gender));
preparedStatement.executeUpdate();
preparedStatement.close();
connection.close();

}catch(SQLException sqlException)
{
throw new DLException(sqlException.getMessage());
}
}

public void update(StudentDTOInterface student) throws DLException
{
if(student==null)
throw new DLException("Student required to update");

int rollNumber=student.getRollNumber();
String name=student.getName();
char gender=student.getGender();

if(rollNumber<=0)
throw new DLException("Invalid roll number, cannot update");

if(name==null)
throw new DLException("Name required, cannot update");

name=name.trim();

if(name.length()==0)
throw new DLException("Name required, cannot update");

if(name.length()>50)
throw new DLException("Name cannot be of more than 50 characters, cannot update");

if("MmFf".indexOf(gender)==-1)
throw new DLException("Invalid gender, cannot update");

if(gender=='m') gender='M';
if(gender=='f') gender='F';

try
{
Connection connection;
connection=DBConnection.connect();

PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement(
"select gender from student where roll_number=?"
);

preparedStatement.setInt(1,rollNumber);

ResultSet resultSet;
resultSet=preparedStatement.executeQuery();

if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();

throw new DLException(rollNumber+" does not exist, cannot update");
}

resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement(
"update student set name=?,gender=? where roll_number=?"
);

preparedStatement.setString(1,name);
preparedStatement.setString(2,String.valueOf(gender));
preparedStatement.setInt(3,rollNumber);

preparedStatement.executeUpdate();

preparedStatement.close();
connection.close();
}
catch(SQLException sqlException)
{
throw new DLException(sqlException.getMessage());
}
}

public void removeByRollNumber(int rollNumber) throws DLException
{
if(rollNumber<=0)
throw new DLException("Invalid roll number, cannot remove");

try
{
Connection connection;
connection=DBConnection.connect();

PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement(
"select gender from student where roll_number=?"
);

preparedStatement.setInt(1,rollNumber);

ResultSet resultSet;
resultSet=preparedStatement.executeQuery();

if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();

throw new DLException(rollNumber+" does not exist, cannot remove");
}

resultSet.close();
preparedStatement.close();

preparedStatement=connection.prepareStatement(
"delete from student where roll_number=?"
);

preparedStatement.setInt(1,rollNumber);
preparedStatement.executeUpdate();

preparedStatement.close();
connection.close();
}
catch(SQLException sqlException)
{
throw new DLException(sqlException.getMessage());
}
}

public StudentDTOInterface getByRollNumber(int rollNumber) throws DLException
{
if(rollNumber<=0)
throw new DLException("Invalid roll number, cannot fetch");

try
{
Connection connection;
connection=DBConnection.connect();

PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement(
"select * from student where roll_number=?"
);

preparedStatement.setInt(1,rollNumber);

ResultSet resultSet;
resultSet=preparedStatement.executeQuery();

if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();

throw new DLException(rollNumber+" does not exist, cannot fetch");
}

String name;
String genderString;
char gender;

rollNumber=resultSet.getInt("roll_number");

name=resultSet.getString("name");
if(name!=null) name=name.trim();
else name="";

genderString=resultSet.getString("gender");
if(genderString!=null) genderString=genderString.trim();
else genderString="M";

gender=genderString.charAt(0);

StudentDTOInterface studentDTOInterface;
studentDTOInterface=new StudentDTO();

studentDTOInterface.setRollNumber(rollNumber);
studentDTOInterface.setName(name);
studentDTOInterface.setGender(gender);

resultSet.close();
preparedStatement.close();
connection.close();

return studentDTOInterface;
}
catch(SQLException sqlException)
{
throw new DLException(sqlException.getMessage());
}
}

public List<StudentDTOInterface> getAll() throws DLException
{
try
{
Connection connection;
connection=DBConnection.connect();

Statement statement;
statement=connection.createStatement();

ResultSet resultSet;
resultSet=statement.executeQuery("select * from student");

StudentDTOInterface studentDTOInterface;
int rollNumber;
String name;
String genderString;
char gender;

List<StudentDTOInterface> students;
students=new ArrayList<>();

while(resultSet.next())
{
rollNumber=resultSet.getInt("roll_number");

name=resultSet.getString("name");
if(name!=null) name=name.trim();
else name="";

genderString=resultSet.getString("gender");
if(genderString!=null) genderString=genderString.trim();
else genderString="M";

gender=genderString.charAt(0);

studentDTOInterface=new StudentDTO();

studentDTOInterface.setRollNumber(rollNumber);
studentDTOInterface.setName(name);
studentDTOInterface.setGender(gender);

students.add(studentDTOInterface);
}

resultSet.close();
statement.close();
connection.close();

return students;
}
catch(SQLException sqlException)
{
throw new DLException(sqlException.getMessage());
}
}

public boolean exist(int rollNumber) throws DLException
{
if(rollNumber<=0)
return false;

try
{
Connection connection;
connection=DBConnection.connect();

PreparedStatement preparedStatement;
preparedStatement=connection.prepareStatement(
"select gender from student where roll_number=?"
);

preparedStatement.setInt(1,rollNumber);

ResultSet resultSet;
resultSet=preparedStatement.executeQuery();

if(!resultSet.next())
{
resultSet.close();
preparedStatement.close();
connection.close();

return false;
}

resultSet.close();
preparedStatement.close();
connection.close();

return true;
}
catch(SQLException sqlException)
{
throw new DLException(sqlException.getMessage());
}
}

}