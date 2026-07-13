package com.threefour.backend.ruchitha;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Ruchitha {

    @Id
    private int id;
    private int age;
    private String name;

    public Ruchitha(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public Ruchitha(){

    }

    public int getId(){
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public int getAge(){
        return age;
    }

    public void setAge(){
        this.age = age;
    }

    public String getName(){
        return name;
    }

    public void setName(){
        this.name = name;
    }
}
