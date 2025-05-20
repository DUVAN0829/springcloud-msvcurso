package co.duvan.springcloud.cursos.services;

import co.duvan.springcloud.cursos.clients.UserClientRest;
import co.duvan.springcloud.cursos.model.User;
import co.duvan.springcloud.cursos.model.entities.Curso;
import co.duvan.springcloud.cursos.model.entities.CursoUser;
import co.duvan.springcloud.cursos.repositories.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CursoServicesImpl implements CursoServices {

    //* Vars
    @Autowired
    private CursoRepository repository;

    @Autowired
    private UserClientRest clientRest;

    //*Methods
    @Override
    @Transactional(readOnly = true)
    public List<Curso> listAll() {
        return (List<Curso>) repository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Curso> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional
    public Curso save(Curso curso) {
        return repository.save(curso);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    //* Methods MSVC

    @Override
    @Transactional
    public Optional<User> asignarUser(User user, Long cursoId) {

        Optional<Curso> o = repository.findById(cursoId);

        if (o.isPresent()) {

            User userMsvc = clientRest.detail(user.getId());

            Curso curso = o.get();
            CursoUser cursoUser = new CursoUser();
            cursoUser.setUserId(userMsvc.getId());

            curso.addCursoUser(cursoUser);
            repository.save(curso);

            return Optional.of(userMsvc);

        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> createUser(User user, Long cursoId) {

        Optional<Curso> o = repository.findById(cursoId);

        if (o.isPresent()) {

            User userNewMsvc = clientRest.create(user);

            Curso curso = o.get();
            CursoUser cursoUser = new CursoUser();
            cursoUser.setUserId(userNewMsvc.getId());

            curso.addCursoUser(cursoUser);
            repository.save(curso);

            return Optional.of(userNewMsvc);

        }

        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<User> deleteUser(User user, Long cursoId) {

        Optional<Curso> o = repository.findById(cursoId);

        if (o.isPresent()) {

            User userMsvc = clientRest.detail(user.getId());

            Curso curso = o.get();
            CursoUser cursoUser = new CursoUser();
            cursoUser.setUserId(userMsvc.getId());

            curso.delete(cursoUser);
            repository.save(curso);

            return Optional.of(userMsvc);

        }

        return Optional.empty();

    }

}
