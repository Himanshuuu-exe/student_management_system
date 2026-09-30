import com.school.datalayer.exceptions.*;
import com.school.datalayer.dao.interfaces.*;
import com.school.datalayer.dto.interfaces.*;
import com.school.datalayer.dao.*;
import com.school.datalayer.dto.*;

import java.util.*;
import java.io.*;

class StudentExixtsTestCase
{
public static void main(String gg[])
{
int rollNumber=Integer.parseInt(gg[0]);
try
{

StudentDAOInterface studentDAOInterface;
studentDAOInterface=new StudentDAO();


System.out.println(studentDAOInterface.exist(rollNumber));


}catch(DLException dlException)
{
System.out.println(dlException);
}

}
}	