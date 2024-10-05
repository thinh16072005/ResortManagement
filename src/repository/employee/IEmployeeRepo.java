package repository.employee;

import java.util.ArrayList;
import model.person.Employee;
import repository.Repository;
/*Employee repo used for reading and writing employee's data*/

public interface IEmployeeRepo extends Repository<Employee, ArrayList<Employee>> {
    
    String employeePath = "\\data\\employee.csv";

    @Override
    void writeFile(ArrayList<Employee> employee);

    @Override
    ArrayList<Employee> readfile();
}
