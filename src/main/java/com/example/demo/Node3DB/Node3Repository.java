package com.example.demo.Node3DB;
import com.example.demo.Node1DB.Node1;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Node3Repository extends JpaRepository<Node3, Long> {
    Node3 findFirstByOrderByIdDesc();
}
