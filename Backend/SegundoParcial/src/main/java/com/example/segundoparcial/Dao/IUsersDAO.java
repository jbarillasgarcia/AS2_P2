package com.example.segundoparcial.Dao;

import com.example.segundoparcial.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
/*
Capa para persistir datos de usuarios a la base de datos
@author: Danilo Estuardo Itzep Luna
@version: 1
 */
public interface IUsersDAO extends JpaRepository<Users,Integer> {
}
