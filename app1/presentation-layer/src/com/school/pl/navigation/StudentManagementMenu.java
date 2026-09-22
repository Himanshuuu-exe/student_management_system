package com.school.pl.navigation;

import java.io.*;
import com.io.stdin.*;
import com.school.pl.*;

public class StudentManagementMenu
{
public void displayMenu() throws IOException
{
int choice;
StudentUI studentUI=new StudentUI();
while(true)
{
System.out.println("1. Add Student");
System.out.println("2. Edit Student");
System.out.println("3. Delete Student");
System.out.println("4. Search Student");
System.out.println("5. Display List of Student");
System.out.println("6. Exit ");

choice=Keyboard.readInt("Enter your choice (1-6) ");
if(choice == 1) studentUI.addStudent();
else if(choice == 2) studentUI.updateStudent();
else if(choice == 3) studentUI.removeStudent();
else if(choice == 4) studentUI.searchStudent();
else if(choice == 5) studentUI.displayList();
else if(choice == 6) break;
else Keyboard.wait("Invalid Choice");
}
}
}