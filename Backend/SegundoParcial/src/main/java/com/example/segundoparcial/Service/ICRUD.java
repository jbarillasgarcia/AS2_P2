package com.example.segundoparcial.Service;

import java.util.List;
import java.util.Optional;
/*
Interfaz para las operaciones principales CRUD
@author: Danilo Estuardo Itzep Luna
@version: 1
 */
public interface ICRUD<T>{
    List<T> getAll();
    Optional<T> get();
    void delete(int id);
    T add(T object);
    T update(T object);
}
