package com.example.demo.controller;

import com.example.demo.Node3DB.Node3;
import com.example.demo.service.Node3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Node3Controller {
    @Autowired
    Node3Service node3Service;

    @RequestMapping(value = "/addnode3",params = {"message"})
    public Node3 addNode3(@RequestParam String message){
        return node3Service.addNode3(new Node3(message));
    }


    @RequestMapping(value = "/deletenode3",params = {"id"})
    public Node3 deleteNode3(@RequestParam Long id){
        return node3Service.deleteNode3ById(id);
    }

    @RequestMapping(value = "/getnode3byid",params = {"id"})
    public Node3 getNode3ById(@RequestParam Long id){
        Node3 n3 = node3Service.getNode3ById(id);
        return n3;
    }

    @RequestMapping(value="/getallnode3", method= RequestMethod.GET)
    public List<Node3> getNode3DatabaseData() {
        List<Node3> list = node3Service.getNode3DatabaseData();
        return list;
    }
}
