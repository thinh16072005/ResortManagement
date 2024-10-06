package service.employee;

import model.person.Employee;
import service.Service;

public interface IEmployeeService extends Service<Employee> {

    @Override
    void save();

    @Override
    void add(Employee employee);

    @Override
    void display();

    @Override
    Employee find(String entity) throws Exception;
    
    void update(Employee e);
    
}
