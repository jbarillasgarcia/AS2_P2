package com.example.segundoparcial.Dao;

import com.example.segundoparcial.Model.HardwareType;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.jpa.repository.JpaRepository;
/*
Capa para persistir datos de tipos de hardware a la base de datos
@author: Danilo Estuardo Itzep Luna
@version: 1
 */
public interface IHardwareTypeDAO extends JpaRepository<HardwareType,Integer> {
}
