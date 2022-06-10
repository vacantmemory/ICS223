package ICS223.demo.database;

import javax.persistence.*;

@Entity
public class Node {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String message;

    public Node() {}

    public Node(String message){
        this.message = message;
    }
    public Node(Long id, String message) {
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
        return "Node{" +
                "id=" + id +
                ", message='" + message + '\'' +
                '}';
    }
}
