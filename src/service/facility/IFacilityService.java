/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.facility;

import model.Facility;
import service.Service;

/**
 *
 * @author hungt
 */
public interface IFacilityService extends Service<Facility> {
    @Override
    Facility find(String entity) throws Exception;

    @Override
    void display();

    @Override
    void add(Facility entity);

    @Override
    void save();
}
