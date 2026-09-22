package com.school;

import com.school.pl.navigation.*;
import java.io.*;

public class Main
{
public static void main(String gg[])
{
StudentManagementMenu studentManagementMenu;
studentManagementMenu=new StudentManagementMenu();
try
{
studentManagementMenu.displayMenu();
}catch(IOException ioException)
{
System.out.println(ioException);
}
}
}