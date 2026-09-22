import com.school.pl.*;
import java.io.*;

class AddStudentTestCase
{

public static void main(String gg[])
{
try
{
StudentUI studentUI=new StudentUI();
studentUI.addStudent();

}catch(IOException ioException)
{
System.out.println(ioException.getMessage() );
}

}

}