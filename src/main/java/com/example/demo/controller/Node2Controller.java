package com.example.demo.controller;

import com.example.demo.Node2DB.Node2;
import com.example.demo.service.Node2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Node2Controller {
    @Autowired
    Node2Service node2Service;

    @RequestMapping(value = "/getnode2byid",params = {"id"})
    public Node2 getNode2ById(@RequestParam Long id){
        Node2 n2 = node2Service.getNode2ById(id);
        return n2;
    }

    @RequestMapping(value = "/addnode2",params = {"message"})
    public Node2 addNode2(@RequestParam String message){
        return node2Service.addNode2(new Node2(message));
    }


    @RequestMapping(value = "/deletenode2",params = {"id"})
    public Node2 deleteNode2(@RequestParam Long id){
        return node2Service.deleteNode2ById(id);
    }

    @RequestMapping(value="/getallnode2", method= RequestMethod.GET)
    public List<Node2> getNode2DatabaseData() {
        List<Node2> list = node2Service.getNode2DatabaseData();
        return list;
    }
}
