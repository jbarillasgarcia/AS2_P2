package com.example.segundoparcial.Controller;

import com.example.segundoparcial.ImplementedServices.HardwareTypeService;
import com.example.segundoparcial.Model.HardwareType;
import com.example.segundoparcial.Utils.JsonResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
/*
Este controlador gestiona el flujo de información entre la vista y modelo de la entidad de TiposDeHardware
@author: Danilo Estuardo Itzep Luna
@version: 1
 */
@RestController
@RequestMapping("/HardwareType")
public class HardwareTypeController {
    @Autowired
    private HardwareTypeService hardwareTypeService;

    /*
    @return: devuelve el listado completo de tipos de hardware
    @version: 1
     */
    @GetMapping("")
    public ResponseEntity<Object> index(){
        return new ResponseEntity<Object>(new JsonResponse(
                "Success",1,hardwareTypeService.getAll(),"Tipos de hardware obtenidos correctamente."
        ), HttpStatus.OK);
    }

    @PostMapping("/New")
    public ResponseEntity<Object> create(@RequestBody HardwareType hardwareType){
        return new ResponseEntity<>("hola",HttpStatus.OK);
    }

    @DeleteMapping("/Delete")
    public ResponseEntity<Object> delete(@RequestParam int id){
        return new ResponseEntity<>("hola",HttpStatus.OK);
    }

    @PutMapping("/Update")
    public ResponseEntity<Object> update(@RequestBody HardwareType hardwareType){
        return new ResponseEntity<>("hola",HttpStatus.OK);
    }
}
