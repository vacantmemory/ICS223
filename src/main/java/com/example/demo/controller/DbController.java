package com.example.demo.controller;

import com.example.demo.Node2DB.Node2;
import com.example.demo.Node2DB.Node2Repository;
import com.example.demo.Node3DB.Node3;
import com.example.demo.Node3DB.Node3Repository;
import com.example.demo.Node1DB.Node1Repository;
import com.example.demo.Node1DB.Node1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DbController {
    @Autowired
    Node1Repository node1Repository;

    @Autowired
    Node2Repository node2Repository;

    @Autowired
    Node3Repository node3Repository;

    @RequestMapping(value="/node1", method= RequestMethod.GET)
    public List<Node1> getNode1DatabaseData() {
        List<Node1> list = node1Repository.findAll();
        return list;
    }

    @RequestMapping(value="/node2", method=RequestMethod.GET)
    public List<Node2> getNode2DatabaseData() {
        List<Node2> list = node2Repository.findAll();
        return list;
    }

    @RequestMapping(value="/node3", method=RequestMethod.GET)
    public List<Node3> getNode3DatabaseData() {
        List<Node3> list = node3Repository.findAll();
        return list;
    }
}
