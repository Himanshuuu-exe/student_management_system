import com.school.datalayer.dto.*;
import com.school.datalayer.dao.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.exceptions.*;

import java.io.*;
import java.util.*;

class StudentRemoveByRollNumberTestCase
{
public static void main(String gg[])
{

int rollNumber=Integer.parseInt(gg[0]);

try
{
StudentDAOInterface studentDAOInterface;

studentDAOInterface=new StudentDAO();
studentDAOInterface.removeByRollNumber(rollNumber);
System.out.println("Removed");

}catch(DLException dlException)
{
System.out.println(dlException);
}

}
}