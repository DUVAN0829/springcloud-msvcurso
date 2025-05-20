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

    Optional<User> asignarUser(User user, Long idCurso);
}
