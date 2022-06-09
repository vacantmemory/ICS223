package com.example.demo.controller;

import com.example.demo.Node1DB.Node1;
import com.example.demo.service.Node1Service;
import com.example.demo.service.Node2Service;
import com.example.demo.service.Node3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {
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


    // /read -> most recent max_id + message
    // /commit -> leader node local commit - > store key words (query/add/delete) in log
    // -> node2+3 check log -> do ops according to the log
    // /read_only -> r1
}
