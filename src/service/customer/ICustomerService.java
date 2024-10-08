/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.customer;

import service.Service;

/**
 *
 * @author hungt
 */
public interface ICustomerService extends Service {

    @Override
    public void save();

    @Override
    public void add(Object entity);

    @Override
    public void display();

    @Override
    public Object find(String entity) throws Exception;

    
    
}
