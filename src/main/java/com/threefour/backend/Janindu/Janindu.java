package com.threefour.backend.Janindu;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Janindu {
    @Id
    public int age ;
    public String name ;

    public Janindu(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public Janindu(){
    }

    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setName(String name) {
        this.name = name;
    }





}
