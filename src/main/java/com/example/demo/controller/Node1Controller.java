package com.example.demo.controller;

import com.example.demo.Node1DB.Node1;
import com.example.demo.service.Node1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Node1Controller {
    @Autowired
    Node1Service node1Service;

    @RequestMapping(value = "/getnode1byid",params = {"id"})
    public Node1 getNode1ById(@RequestParam Long id){
        Node1 n1 = node1Service.getNode1ById(id);
        return n1;
    }

    @RequestMapping(value = "/addnode1",params = {"message"})
    public Node1 addNode1(@RequestParam String message){
        return node1Service.addNode1(new Node1(message));
    }

    @RequestMapping(value = "/deletenode1",params = {"id"})
    public Node1 deleteNode1(@RequestParam Long id){
        return node1Service.deleteNode1ById(id);
    }

    @RequestMapping(value="/getallnode1", method= RequestMethod.GET)
    public List<Node1> getNode1DatabaseData() {
        List<Node1> list = node1Service.getNode1DatabaseData();
        return list;
    }
}
