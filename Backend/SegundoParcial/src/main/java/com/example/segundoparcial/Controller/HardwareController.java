package com.example.segundoparcial.Controller;

import com.example.segundoparcial.ImplementedServices.HardwareService;
import com.example.segundoparcial.Utils.JsonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
Este controlador gestiona el flujo de información entre la vista y modelo de la entidad Hardware
@author: Danilo Estuardo Itzep Luna
@return: devuelve el listado completo de hardware
@version: 1
 */
@RestController
@RequestMapping("/Hardware")
public class HardwareController {
    @Autowired
    private HardwareService hardwareService;

/*
Se maneja toda la respuesta HTTP incluyendo el cuerpo, cabecera y códigos de estado permitiendo configurar la respuesta a enviar  desde nuestro endpoint
*/
    @GetMapping("")
    public ResponseEntity<Object> index(){
        return new ResponseEntity<>(new JsonResponse("Success",11,hardwareService.getAll(),"Hardware recuperado correctamente"), HttpStatus.OK);
    }
}
