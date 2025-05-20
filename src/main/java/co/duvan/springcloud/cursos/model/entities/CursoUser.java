package co.duvan.springcloud.cursos.model.entities;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "cursos_users")
public class CursoUser {

    //*Vars
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", unique = true)
    private Long userId;

    //* Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    //* Mehods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CursoUser cursoUser = (CursoUser) o;
        return Objects.equals(id, cursoUser.id) && Objects.equals(userId, cursoUser.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId);
    }

}
