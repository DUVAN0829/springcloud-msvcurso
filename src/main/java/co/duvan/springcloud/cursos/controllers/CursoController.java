package co.duvan.springcloud.cursos.controllers;

import co.duvan.springcloud.cursos.model.User;
import co.duvan.springcloud.cursos.model.entities.Curso;
import co.duvan.springcloud.cursos.services.CursoServices;
import feign.FeignException;
import feign.Response;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class CursoController {

    //Vars
    @Autowired
    private CursoServices service;

    //Methods

    //*List
    @GetMapping
    public ResponseEntity<List<Curso>> list() {
        return ResponseEntity.ok(service.listAll());
    }

    //*GetById
    @GetMapping("/{id}")
    public ResponseEntity<?> detail(@PathVariable Long id) {

        Optional<Curso> cursoOptional = service.byIdWithUser(id);

        if (cursoOptional.isPresent()) {
            return ResponseEntity.ok(cursoOptional.get());
        }

        return ResponseEntity.notFound().build();

    }

    //*Create
    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Curso curso, BindingResult result) {

        if (result.hasErrors()) {
            return validBodyRequest(result);
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(curso));

    }

    //*Update
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Curso curso, @PathVariable Long id) {

        Optional<Curso> cursoOptional = service.findById(id);

        if (cursoOptional.isPresent()) {

            Curso cursoDB = cursoOptional.get();

            cursoDB.setName(curso.getName());

            return ResponseEntity.status(HttpStatus.CREATED).body(service.save(cursoDB));

        }

        return ResponseEntity.notFound().build();

    }

    //*Delete
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {

        Optional<Curso> cursoOptional = service.findById(id);

        if (cursoOptional.isPresent()) {

            service.delete(id);
            return ResponseEntity.noContent().build();

        }

        return ResponseEntity.notFound().build();

    }

    //* Method: valid body request
    public ResponseEntity<?> validBodyRequest(BindingResult result) {

        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(error -> {
            errors.put(error.getField(), " el campo " + error.getField() + " " + error.getDefaultMessage());
        });

        return ResponseEntity.badRequest().body(errors);

    }

    //* Methods MSVC

    //* Asignar user
    @PutMapping("/asignar-user/{cursoId}")
    public ResponseEntity<?> asignarUser(@RequestBody User user, @PathVariable Long cursoId) {

        Optional<User> o;

        try {
            o = service.asignarUser(user, cursoId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Message", "User not exist by id or communication error " + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }

        return ResponseEntity.notFound().build();

    }

    //* Create user
    @PostMapping("/create-user/{cursoId}")
    public ResponseEntity<?> createUser(@RequestBody User user, @PathVariable Long cursoId) {

        Optional<User> o;

        try {
            o = service.createUser(user, cursoId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Message", "User cannot be created or communication error " + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(o.get());
        }

        return ResponseEntity.notFound().build();

    }

    //* Delete user
    @DeleteMapping("/delete-user/{cursoId}")
    public ResponseEntity<?> deleteUser(@RequestBody User user, @PathVariable Long cursoId) {

        Optional<User> o;

        try {
            o = service.deleteUser(user, cursoId);
        } catch (FeignException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.singletonMap("Message", "User not exist by id or communication error " + e.getMessage()));
        }

        if (o.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(o.get());
        }

        return ResponseEntity.notFound().build();

    }

    //* Delete CursoUser By id
    @DeleteMapping("delete-curso-user/{id}")
    public ResponseEntity<?> deleteCursoUser(@PathVariable Long id) {

        service.deleteCursoUserById(id);

        return ResponseEntity.noContent().build();

    }

}




























