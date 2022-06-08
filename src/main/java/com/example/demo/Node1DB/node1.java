package com.example.demo.Node1DB;

import javax.persistence.*;

@Entity
public class node1 {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String message;

    public node1() {}

    public node1(String message){
        this.message = message;
    }
    public node1(Long id, String message) {
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
        return "Node1{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
