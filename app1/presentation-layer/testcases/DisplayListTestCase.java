import com.school.pl.*;
import java.io.*;

class DisplayListTestCase
{

public static void main(String gg[])
{
try
{
StudentUI studentUI=new StudentUI();
studentUI.displayList();

}catch(IOException ioException)
{
System.out.println(ioException.getMessage() );
}

}

}