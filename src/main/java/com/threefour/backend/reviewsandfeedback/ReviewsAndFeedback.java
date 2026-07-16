package com.threefour.backend.reviewsandfeedback;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReviewsAndFeedback {


    @Id
    public int id;
    public int age;
    public String name;

    public ReviewsAndFeedback(int id, int age, String name) {
        this.id = id;
        this.age = age;
        this.name = name;
    }

    public ReviewsAndFeedback(){
    }

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


}
