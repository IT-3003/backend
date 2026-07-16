package com.threefour.backend.Anshu;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Anshu {
    @Id
    public int id;
    public int age;
    public String name;

    public Anshu(int id,int age,String name) {
     this.id = id;
     this.age = age;
     this.name = name;


    }
    public Anshu(){

    }
    public int getId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGetId() {
        return getId;
    }

    public void setGetId(int getId) {
        this.getId = getId;
    }
}
