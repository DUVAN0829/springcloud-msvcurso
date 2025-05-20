package co.duvan.springcloud.cursos.model.entities;

import co.duvan.springcloud.cursos.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "cursos")
public class Curso {

    //* Vars
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "curso_id")
    private List<CursoUser> cursoUsers;

    @Transient
    private List<User> users;

    //* Constructor
    public Curso() {
        this.cursoUsers = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    //* Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CursoUser> getCursoUsers() {
        return cursoUsers;
    }

    public void setCursoUsers(List<CursoUser> cursoUsers) {
        this.cursoUsers = cursoUsers;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    //* Methods
    public void addCursoUser(CursoUser cursoUser) {
        this.cursoUsers.add(cursoUser);
    }

    public void delete(CursoUser cursoUser) {
        this.cursoUsers.remove(cursoUser);
    }

}











