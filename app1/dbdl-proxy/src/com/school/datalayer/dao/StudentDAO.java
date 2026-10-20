package com.school.datalayer.dao;

import com.google.gson.*;
import com.school.network.*;
import java.sql.*;
import java.util.*;
import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.exceptions.*;
import com.school.datalayer.dto.*; public class StudentDAO implements 

StudentDAOInterface
{

public void add(StudentDTOInterface student) throws DLException
{
Request request=new Request("STUDENT","ADD",student);
Gson gson=new Gson();
String jsonString=gson.toJson(request);
System.out.println(jsonString);
}

public void update(StudentDTOInterface student) throws DLException
{
throw new DLException("Not yet implemented");
}

public void removeByRollNumber(int rollNumber) throws DLException
{
throw new DLException("Not yet implemented");
}

public StudentDTOInterface getByRollNumber(int rollNumber) throws DLException
{
throw new DLException("Not yet implemented");
}

public List<StudentDTOInterface> getAll() throws DLException
{
throw new DLException("Not yet implemented");
}

public boolean exist(int rollNumber) throws DLException
{
throw new DLException("Not yet implemented");
}

}