package com.school.datalayer.dao;

import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.dto.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.exceptions.*;

import java.util.*;
import java.io.*;

public class StudentDAO implements StudentDAOInterface
{
public final static String DATA_FILE="students.data";

public void add(StudentDTOInterface student) throws DLException
{
if(student==null) throw new DLException("Student can not be null");

int rollNumber=student.getRollNumber();
String name=student.getName();
char gender=student.getGender();

if(rollNumber<=0) throw new DLException("RN cant be 0 or -ive");

if(name==null) throw new DLException("name cant be null");

name=name.trim();

if(name.length()==0) throw new DLException("Name length cant be 0");

if(name.length()>50) throw new DLException("name can not excceed 50char limit");

if("MmFf".indexOf(gender)==-1) throw new DLException("Expect only M/F");

try
{
File file=new File(DATA_FILE);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");

while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
int vRollNumber=Integer.parseInt(randomAccessFile.readLine());
String vName=randomAccessFile.readLine();
char vGender=randomAccessFile.readLine().charAt(0);

if(vRollNumber==rollNumber)
{
randomAccessFile.close();
System.out.println("ROLL NO. ALREADY EXIXTS, (in if rn=vRn)");
}

}

randomAccessFile.writeBytes(rollNumber+"\n");
randomAccessFile.writeBytes(name+"\n");
randomAccessFile.writeBytes(gender+"\n");
randomAccessFile.close();
System.out.println("Student Added (at last of dao)");

}catch(IOException ioException)
{
throw new DLException(ioException.getMessage());
}

} 

public void update(StudentDTOInterface student) throws DLException
{

if(student==null) throw new DLException("Student can not be null");

int rollNumber=student.getRollNumber();
String name=student.getName();
char gender=student.getGender();

if(rollNumber<=0) throw new DLException("RN cant be 0 or -ive");

if(name==null) throw new DLException("name cant be null");

name=name.trim();

if(name.length()==0) throw new DLException("Name length cant be 0");

if(name.length()>50) throw new DLException("name can not excceed 50char limit");

if("MmFf".indexOf(gender)==-1) throw new DLException("Expect only M/F");

try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
throw new DLException(rollNumber+" Not Exists ");
}

RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");

if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DLException(rollNumber+" Not exists");
}

File tmFile=new File("tmp.tmp");
if(tmFile.exists()) tmFile.delete();
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmFile,"rw");

int vRollNumber;
String vName;
char vGender;

boolean found=false;

while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
vRollNumber=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine().charAt(0);

if(vRollNumber!=rollNumber)
{
tmpRandomAccessFile.writeBytes(vRollNumber+"\n");
tmpRandomAccessFile.writeBytes(vName+"\n");
tmpRandomAccessFile.writeBytes(vGender+"\n");
}

else
{
found=true;
tmpRandomAccessFile.writeBytes(rollNumber+"\n");
tmpRandomAccessFile.writeBytes(name+"\n");
tmpRandomAccessFile.writeBytes(gender+"\n");
}

}

if(found==false)
{
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();

throw new DLException(rollNumber+" does not exists");
}

tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);

while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}

//vvimp line
randomAccessFile.setLength(tmpRandomAccessFile.length());

tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();

}catch(IOException ioException)
{
throw new DLException(ioException.getMessage());
}


} 


public void removeByRollNumber(int rollNumber) throws DLException
{


if(rollNumber<=0) throw new DLException("RN cant be 0 or -ive");

try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
throw new DLException(rollNumber+" Not Exists ");
}

RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");

if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DLException(rollNumber+" Not exists");
}

File tmFile=new File("tmp.tmp");
if(tmFile.exists()) tmFile.delete();
RandomAccessFile tmpRandomAccessFile=new RandomAccessFile(tmFile,"rw");

int vRollNumber;
String vName;
char vGender;

boolean found=false;

while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
vRollNumber=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine().charAt(0);

if(vRollNumber!=rollNumber)
{
tmpRandomAccessFile.writeBytes(vRollNumber+"\n");
tmpRandomAccessFile.writeBytes(vName+"\n");
tmpRandomAccessFile.writeBytes(vGender+"\n");
}

else
{
found=true;
}

}

if(found==false)
{
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();

throw new DLException(rollNumber+" does not exists");
}

tmpRandomAccessFile.seek(0);
randomAccessFile.seek(0);

while(tmpRandomAccessFile.getFilePointer() < tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}

//vvimp line
randomAccessFile.setLength(tmpRandomAccessFile.length());

tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.close();
randomAccessFile.close();

}catch(IOException ioException)
{
throw new DLException(ioException.getMessage());
}

} 

public StudentDTOInterface getByRollNumber(int rollNumber) throws DLException
{
if(rollNumber<=0) throw new DLException(rollNumber+"Invalid Roll Number");

try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
throw new DLException("File does not exists");
}

RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");

if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new DLException("file is empty");
}

int vRollNumber;
String vName;
char vGender;

StudentDTOInterface studentDTOInterface;

while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
vRollNumber=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine().charAt(0);

if(rollNumber==vRollNumber)
{
studentDTOInterface=new StudentDTO(vRollNumber,vName,vGender);
randomAccessFile.close();
return studentDTOInterface;
}
}

randomAccessFile.close();
throw new DLException(rollNumber+"Does not exists");

}catch(IOException ioException)
{
throw new DLException(ioException.getMessage());
}
}

public List<StudentDTOInterface> getAll() throws DLException
{
List<StudentDTOInterface> students;

students = new ArrayList<StudentDTOInterface>();

try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
return students;
}

RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file, "rw");

if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return students;
}

StudentDTOInterface studentDTOInterface;
int vRollNumber;
String vName;
char vGender;

while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
vRollNumber=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine().charAt(0);
studentDTOInterface = new StudentDTO(vRollNumber,vName,vGender);
students.add(studentDTOInterface);
}
randomAccessFile.close();
}catch(IOException ioException)
{
throw new DLException(ioException.getMessage());
}

return students;

} 

public boolean exist(int rollNumber) throws DLException
{

if(rollNumber<=0) throw new DLException(rollNumber+"Invalid Roll Number");

try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
return false;
}

RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");

if(randomAccessFile.length()==0)
{
randomAccessFile.close();
return false;
}

int vRollNumber;
String vName;
char vGender;

StudentDTOInterface studentDTOInterface;

while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
vRollNumber=Integer.parseInt(randomAccessFile.readLine());
vName=randomAccessFile.readLine();
vGender=randomAccessFile.readLine().charAt(0);

if(rollNumber==vRollNumber)
{

randomAccessFile.close();
return true;
}
}

randomAccessFile.close();
return false;

}catch(IOException ioException)
{
throw new DLException(ioException.getMessage());
}
} 
}