/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package repository.customer;

import java.util.ArrayList;
import model.person.Customer;
import repository.Repository;

public interface ICustomerRepo extends Repository<Customer, ArrayList<Customer>> {
    
    String customerPath = "\\data\\customer.csv";

    @Override
    public void writeFile(ArrayList<Customer> entities);

    @Override
    ArrayList<Customer> readFile();
}
