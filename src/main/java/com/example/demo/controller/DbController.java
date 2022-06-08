package com.example.demo.controller;

import com.example.demo.Node2DB.node2;
import com.example.demo.Node2DB.Node2Repository;
import com.example.demo.Node3DB.node3;
import com.example.demo.Node3DB.Node3Repository;
import com.example.demo.Node1DB.Node1Repository;
import com.example.demo.Node1DB.node1;
import com.example.demo.service.LeaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DbController {
    @Autowired
    LeaderService leaderService;

    @Autowired
    Node1Repository node1Repository;

    @Autowired
    Node2Repository node2Repository;

    @Autowired
    Node3Repository node3Repository;



    @RequestMapping(value = "/getnode1bymessage",params = {"message"})
    public node1 getNode1ByMessage(@RequestParam String message){
        try {
            return leaderService.getNode1ByMessage(message);
        }catch(Exception e){
            System.out.println(e);
            return new node1(404L, "Node1 doesn't have that message, transaction fails");
        }
    }

    @RequestMapping(value = "/addnode1",params = {"message"})
    public node1 addNode1(@RequestParam String message){
        try {
            return leaderService.addNode1(new node1(message));
        }catch(Exception e){
            System.out.println(e);
            return new node1(404L, "failed to add new node1");
        }
    }

    @RequestMapping(value="/getallnode1", method= RequestMethod.GET)
    public List<node1> getNode1DatabaseData() {
        List<node1> list = node1Repository.findAll();
        return list;
    }

    @RequestMapping(value="/getallnode2", method=RequestMethod.GET)
    public List<node2> getNode2DatabaseData() {
        List<node2> list = node2Repository.findAll();
        return list;
    }

    @RequestMapping(value="/getallnode3", method=RequestMethod.GET)
    public List<node3> getNode3DatabaseData() {
        List<node3> list = node3Repository.findAll();
        return list;
    }
}
