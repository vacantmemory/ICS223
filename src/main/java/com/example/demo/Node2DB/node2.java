package com.example.demo.Node2DB;

import javax.persistence.*;

@Entity
public class node2 {
    @Id
    @SequenceGenerator(
            name = "nodeId",
            sequenceName = "nodeId",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "nodeId"
    )
    private Long id;
    private String message;

    public node2() {}

    public node2(Long id, String message) {
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
