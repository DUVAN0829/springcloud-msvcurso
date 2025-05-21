package co.duvan.springcloud.cursos.services;

import co.duvan.springcloud.cursos.model.User;
import co.duvan.springcloud.cursos.model.entities.Curso;

import java.util.List;
import java.util.Optional;

public interface CursoServices {

    List<Curso> listAll();

    Optional<Curso> findById(Long id);

    Curso save(Curso curso);

    void delete(Long id);

    Optional<User> asignarUser(User user, Long cursoId); //* Asigna a un usuario existente en el curso

    Optional<User> createUser(User user, Long cursoId); //* Crea un usuario y lo asigna al curso

    Optional<User> deleteUser(User user, Long cursoId); //* Elimina usuario del curso no de la base de datos.

    Optional<Curso> byIdWithUser(Long id);

}
