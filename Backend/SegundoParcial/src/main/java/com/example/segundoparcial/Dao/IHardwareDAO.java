package com.example.segundoparcial.Dao;

import com.example.segundoparcial.Model.Hardware;
import org.springframework.data.jpa.repository.JpaRepository;
/*
Capa para persistir datos de Hardware a la base de datos
@author: Danilo Estuardo Itzep Luna
@version: 1
 */
public interface IHardwareDAO extends JpaRepository<Hardware,Integer> {

}
