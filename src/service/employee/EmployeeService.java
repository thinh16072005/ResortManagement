/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.employee;

import model.person.Employee;
import repository.employee.EmployeeRepo;

/**
 *
 * @author hungt
 */
public class EmployeeService extends EmployeeRepo implements IEmployeeService {

    @Override
    public void save() {
        
    }

    @Override
    public void add(Employee employee) {
        
    }

    @Override
    public void display() {
        for (Employee emp : empList) {
            System.out.println(emp);
        }
    }

    @Override
    public Employee find(String id) {
        
    }

    @Override
    public void update(Employee e) {
        
    }
    
}
