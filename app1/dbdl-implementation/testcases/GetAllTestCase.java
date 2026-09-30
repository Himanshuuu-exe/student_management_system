import com.school.datalayer.dto.*;
import com.school.datalayer.dao.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.exceptions.*;

import java.io.*;
import java.util.*;

class GetAllTestCase
{
public static void main(String gg[])
{
try
{
List<StudentDTOInterface> s;
StudentDAOInterface studentDAOInterface;
StudentDTOInterface student;

studentDAOInterface=new StudentDAO();
s=studentDAOInterface.getAll();

for(int i=0; i<s.size(); i++)
{
student=s.get(i);
System.out.println(student.getRollNumber() + "," + student.getName() + "," + student.getGender());
}
}catch(DLException dlException)
{
System.out.println(dlException);
}

}
}