package co.duvan.springcloud.cursos.controllers;

import co.duvan.springcloud.cursos.model.entities.Curso;
import co.duvan.springcloud.cursos.services.CursoServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

        Optional<Curso> cursoOptional = service.findById(id);

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

}




























