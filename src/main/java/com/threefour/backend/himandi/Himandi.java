package com.threefour.backend.himandi;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity

public class Himandi {
    @Id
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public Himandi(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String name;
    public int age;

    public Himandi() {


    }
}
