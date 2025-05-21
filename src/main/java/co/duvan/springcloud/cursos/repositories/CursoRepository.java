package co.duvan.springcloud.cursos.repositories;

import co.duvan.springcloud.cursos.model.entities.Curso;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

public interface CursoRepository extends CrudRepository<Curso, Long> {

    @Transactional
    @Modifying //* Cada vez que se hagan modificaciones en la BD se usa esta propiedad
    @Query("delete from CursoUser cu where cu.userId=?1")
    void deleteCursoUserById(Long id);

}
