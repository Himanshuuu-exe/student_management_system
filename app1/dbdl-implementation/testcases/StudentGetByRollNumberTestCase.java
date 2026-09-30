import com.school.datalayer.exceptions.*;
import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.dao.*;
import com.school.datalayer.dto.*;

import java.util.*;
import java.io.*;

class StudentGetByRollNumberTestCase
{
public static void main(String gg[])
{
int rollNumber=Integer.parseInt(gg[0]);
try
{
StudentDTOInterface studentDTOInterface;

StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();

studentDTOInterface = studentDAOInterface.getByRollNumber(rollNumber);

System.out.println(studentDTOInterface.getRollNumber());
System.out.println(studentDTOInterface.getName());
System.out.println(studentDTOInterface.getGender());


}catch(DLException dlException)
{
System.out.println(dlException);
}

}
}	