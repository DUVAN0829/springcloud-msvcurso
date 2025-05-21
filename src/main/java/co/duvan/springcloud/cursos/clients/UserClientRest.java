package co.duvan.springcloud.cursos.clients;

import co.duvan.springcloud.cursos.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "msvc-users", url = "localhost:8001")
public interface UserClientRest {

    @GetMapping("/{id}")
    User detail(@PathVariable Long id);

    @PostMapping("/")
    User create(@RequestBody User user);

    @GetMapping("/users-by-curso")
    List<User> getStudentsByCourse(@RequestParam Iterable<Long> ids);

}
