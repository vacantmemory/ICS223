package com.example.demo.Node3DB;

import javax.persistence.*;

@Entity
public class Node3 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String message;

    public Node3() {}

    public Node3(String message){
        this.message = message;
    }

    public Node3(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Node3{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
