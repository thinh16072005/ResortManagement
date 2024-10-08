/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.customer;

import model.person.Customer;
import model.person.Employee;
import service.Service;

public interface ICustomerService extends Service<Customer> {

    @Override
    public void save();

    @Override
    public void add(Customer customer);

    @Override
    public void display();

    @Override
    public Customer find(String entity) throws Exception;

    void update(Customer e);

}
