import com.school.datalayer.dto.*;
import com.school.datalayer.dao.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.exceptions.*;

import java.io.*;
import java.util.*;

class StudentUpdateTestCase
{
public static void main(String gg[])
{

int rollNumber=Integer.parseInt(gg[0]);
String name=gg[1];
char gender=gg[2].charAt(0);

try
{
StudentDAOInterface studentDAOInterface;
StudentDTOInterface studentDTOInterface;
studentDTOInterface=new StudentDTO(rollNumber,name,gender);

studentDAOInterface=new StudentDAO();
studentDAOInterface.update(studentDTOInterface);
System.out.println("Updated");

}catch(DLException dlException)
{
System.out.println(dlException);
}

}
}