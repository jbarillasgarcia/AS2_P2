package com.example.segundoparcial.Model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
/*
Modelo para acceder a la capa de almacenamiento de datos de la entidad tipos de hardware
@author: Danilo Estuardo Itzep Luna
@version: 1
 */
@Entity(name = "TblTipoHardware")
public class HardwareType {
    private @Id
    @GeneratedValue
    int IdTipoHardware;

    @NotBlank(message = "Descripción no puede estar en blanco.")
    @Column
    private String Descripcion;

    @NotBlank(message = "Fecha de creación no puede estar en blanco.")
    @Column
    private Date FechaCreacion;

    public int getIdTipoHardware() {
        return IdTipoHardware;
    }

    public void setIdTipoHardware(int idTipoHardware) {
        IdTipoHardware = idTipoHardware;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public Date getFechaCreacion() {
        return FechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        FechaCreacion = fechaCreacion;
    }
}
