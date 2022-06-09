package com.example.demo.controller;

import com.example.demo.Node2DB.Node2;
import com.example.demo.Node3DB.Node3;
import com.example.demo.Node1DB.Node1;
import com.example.demo.service.Node1Service;
import com.example.demo.service.Node2Service;
import com.example.demo.service.Node3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DbController {
    @Autowired
    Node1Service node1Service;

    @Autowired
    Node2Service node2Service;

    @Autowired
    Node3Service node3Service;

    @RequestMapping(value = "/read")
    public Node1 getLatestMessage(){
        Node1 n1= node1Service.getNode1LatestMessage();
        return n1;
    }


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

    @RequestMapping(value = "/getnode3byid",params = {"id"})
    public Node3 getNode3ById(@RequestParam Long id){
        Node3 n3 = node3Service.getNode3ById(id);
        return n3;
    }

    @RequestMapping(value = "/addnode3",params = {"message"})
    public Node3 addNode3(@RequestParam String message){
        return node3Service.addNode3(new Node3(message));
    }

    @RequestMapping(value = "/deletenode3",params = {"id"})
    public Node3 deleteNode3(@RequestParam Long id){
        return node3Service.deleteNode3ById(id);
    }


    // /read -> most recent max_id + message
    // /commit -> leader node local commit - > store key words (query/add/delete) in log
    // -> node2+3 check log -> do ops according to the log
    // /read_only -> r1


    @RequestMapping(value="/getallnode1", method= RequestMethod.GET)
    public List<Node1> getNode1DatabaseData() {
        List<Node1> list = node1Service.getNode1DatabaseData();
        return list;
    }

    @RequestMapping(value="/getallnode2", method=RequestMethod.GET)
    public List<Node2> getNode2DatabaseData() {
        List<Node2> list = node2Service.getNode2DatabaseData();
        return list;
    }

    @RequestMapping(value="/getallnode3", method=RequestMethod.GET)
    public List<Node3> getNode3DatabaseData() {
        List<Node3> list = node3Service.getNode3DatabaseData();
        return list;
    }
}
