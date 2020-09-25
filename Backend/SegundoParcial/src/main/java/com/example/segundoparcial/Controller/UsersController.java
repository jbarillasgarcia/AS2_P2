package com.example.segundoparcial.Controller;

import com.example.segundoparcial.ImplementedServices.UsersService;
import com.example.segundoparcial.Model.Users;
import com.example.segundoparcial.Utils.JsonResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
/*
Este controlador gestiona el flujo de información entre la vista y modelo de la entidad Usuarios
@author: Danilo Estuardo Itzep Luna
@version: 1
 */
@RestController
@RequestMapping("/Users")
public class UsersController {
    @Autowired
    private UsersService usersService;

    /*
    @return: devuelve el listado completo de usuarios
    @version: 1
    */
    @GetMapping("")
    public ResponseEntity<Object> index(){
        return new ResponseEntity<>(new JsonResponse("Success",21,usersService.getAll(),"Usuarios obtenidos correctamente."), HttpStatus.OK);
    }

    /*
   @return: metodo para eliminar usuario por id
   @param: recibe el parámetro id del usuario
   @version: 1
   */
    @DeleteMapping("/Delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable int id){
        usersService.delete(id);
        return new ResponseEntity<>(new JsonResponse("Success",22,null,"Usuario eliminado correctamente"),HttpStatus.OK);
    }
    /*
   @return: metodo para agregar un usuario
   @param: recibe el parámetro user
   @version: 1
   */
    @PostMapping("/Create")
    public ResponseEntity<Object> create(@RequestBody Users user){
        return new ResponseEntity<>(new JsonResponse("Success",23,usersService.add(user),"Usuario creado correctamente"),HttpStatus.OK);
    }
    /*
   @return: metodo para actualizar un usuario
   @param: recibe el parámetro user
   @version: 1
   */
    @PutMapping("/Edit")
    public ResponseEntity<Object> edit(@RequestBody Users user){
        System.out.println(new JSONObject(user));
        return new ResponseEntity<>(new JsonResponse("Success",24,usersService.update(user),"Usuario creado correctamente"),HttpStatus.OK);
    }
}
