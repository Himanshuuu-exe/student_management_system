package com.school.pl.pojo; 

public class Student implements Comparable<Student>
{
private int rollNumber;
private String name;
private String gender;

public Student()
{
this.rollNumber=0;
this.name="";
this.gender="";
}

public Student(int rollNumber,String name,String gender)
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

public void setGender(String gender)
{
this.gender=gender;
}

public String getGender()
{
return this.gender;
}

public String toString()
{
return "Roll Number : "+rollNumber+", Name : "+name+", Gender : "+gender;
}

public int compareTo(Student other)
{
return this.rollNumber-other.rollNumber;
}

}