package com.school.datalayer.dao.interfaces;
import com.school.datalayer.exceptions.*;
import com.school.datalayer.dto.interfaces.*;
import java.util.*;

public interface StudentDAOInterface
{
public void add(StudentDTOInterface student) throws DLException;
public void update(StudentDTOInterface student) throws DLException;
public void removeByRollNumber(int rollNumber) throws DLException;
public StudentDTOInterface getByRollNumber(int rollNumber) throws DLException;
public List<StudentDTOInterface> getAll() throws DLException;
public boolean exist(int rollNumber) throws DLException;

}