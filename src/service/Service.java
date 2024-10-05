package service;

/* This service interface will receive commands from users*/
public interface Service<T> {
    T find(String entity);
    
    void display();
    
    void add(T entity);
    
    void save();
}
