package com.example.demo.Node1DB;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Node1Repository extends JpaRepository<Node1, Long> {
    Node1 findFirstByOrderByIdDesc();
}
