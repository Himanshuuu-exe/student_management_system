package com.school.datalayer.dao;

import java.io.*;
import java.sql.*;
import com.school.datalayer.exceptions.*;

class DBConnection
{
//default values of following will be null
static private String driverName;
static private String connectionString;
static private String username;
static private String password;

private DBConnection()
{
}

static
{
try
{
File file=new File("db.conf");
if(file.exists()==false) throw new Exception("db.conf missing");

RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"r");

if(randomAccessFile.length()==0)
{
randomAccessFile.close();
throw new Exception("db.conf is empty/length null");
}

String line;

while(randomAccessFile.getFilePointer() < randomAccessFile.length())
{
line=randomAccessFile.readLine();
line=line.replace(" ","");

if(line.toUpperCase().startsWith("USERNAME="))
{
username=line.substring(9);
}

else if(line.toUpperCase().startsWith("PASSWORD="))
{
password=line.substring(9);
}

else if(line.toUpperCase().startsWith("DRIVER-NAME="))
{
driverName=line.substring(12);
}

else if(line.toUpperCase().startsWith("CONNECTION-STRING="))
{
connectionString=line.substring(18);
}

}

randomAccessFile.close();

if(username==null) throw new Exception("Username missing in db.conf refer to documentation");

if(password==null) throw new Exception("Password missing in db.conf refer to documentation");

if(driverName==null) throw new Exception("Driver name missing in db.conf refer to documentation");

if(connectionString==null) throw new Exception("Connection String missing in db.conf refer to documentation");
}catch(Exception exception)
{
System.out.println(exception.getMessage());
System.exit(1);
}
}

static Connection connect() throws DLException
{
Connection connection;
try
{
Class.forName(driverName);
connection=DriverManager.getConnection(connectionString,username,password);
}catch(Exception exception)
{
throw new DLException(exception.getMessage());
}
return connection;
}

}