package com.example.demo.Node2DB;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Node2Repository extends JpaRepository<Node2, Long> {
}
