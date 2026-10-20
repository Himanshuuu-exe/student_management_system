import com.school.datalayer.dao.*;
import com.school.datalayer.dto.*;
import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.exceptions.*;

class StudentAddTestCase
{
public static void main(String gg[])
{
int rollNumber=Integer.parseInt(gg[0]);
String name=gg[1];
char gender=gg[2].charAt(0);

try
{
StudentDTOInterface studentDTOInterface;
studentDTOInterface=new StudentDTO(rollNumber,name,gender);

StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();

studentDAOInterface.add(studentDTOInterface);
System.out.println("Student Added, (at last of Add test case)");

}catch(DLException dleException)
{
System.out.println(dleException);
}


}
}