package CRUD.testdatabase;

import java.time.LocalDate;

public class User {
    private Integer id;
    private String name;
    private Integer age;
    private Boolean isAdmin;
    private LocalDate date;

    public void setId(int id) {
        this.id = id;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(Boolean admin) {
        isAdmin = admin;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public User() {
    }

    @Override
    public String toString() {
        return  "id = " + id +
                ", name = '" + name +
                ", age = " + age +
                ", isAdmin = " + isAdmin +
                ", date = " + date;
    }
}
