package com.school.datalayer.dto;

import com.school.datalayer.dto.interfaces.*;

public class StudentDTO implements StudentDTOInterface
{
private int rollNumber;
private String name;
private char gender;

public StudentDTO()
{
this.rollNumber=0;
this.name="";
this.gender=(char)0;
}

public StudentDTO(int rollNumber, String name, char gender)
{
this.rollNumber=rollNumber;
this.name=name;
this.gender=gender;
}

public void setRollNumber(int rollNumber)
{
this.rollNumber=rollNumber;
}

public int getRollNumber()
{
return this.rollNumber;
}

public void setName(String name)
{
this.name=name;
}

public String getName()
{
return this.name;
}

public void setGender(char gender)
{
this.gender=gender;
}

public char getGender()
{
return this.gender;
}

public String toString()
{
return "RollNumber is : "+ rollNumber + "name is : " +name+ "gender is : " +gender;
}

}