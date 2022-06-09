package com.example.demo.Node2DB;

import com.example.demo.Node1DB.Node1;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Node2Repository extends JpaRepository<Node2, Long> {
    Node2 findFirstByOrderByIdDesc();
}
