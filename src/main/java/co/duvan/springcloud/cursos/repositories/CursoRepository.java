package co.duvan.springcloud.cursos.repositories;

import co.duvan.springcloud.cursos.entities.Curso;
import org.springframework.data.repository.CrudRepository;

public interface CursoRepository extends CrudRepository<Curso, Long> {


}
