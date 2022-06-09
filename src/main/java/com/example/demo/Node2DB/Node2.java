package com.example.demo.Node2DB;

import javax.persistence.*;

@Entity
public class Node2 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String message;

    public Node2() {}

    public Node2(String message){
        this.message = message;
    }
    public Node2(Long id, String message) {
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
        return "Node2{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
