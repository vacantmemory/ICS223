package com.example.demo.service;

import com.example.demo.Node3DB.Node3;
import com.example.demo.Node3DB.Node3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service("Node3Service")
@Transactional("node3TransactionManager")
public class Node3Service {

    @Autowired
    Node3Repository node3Repository;

    //query
    public List<Node3> getNode3ById(Long id) {
        List<Node3> list = node3Repository.findAllById(Collections.singleton(id));
        if (list.isEmpty()) {
            Node3 Node3 = new Node3(301L, "node3 not found by id.");
            list.add(Node3);
        }

        return list;
    }

    //add
    public Node3 addNode3(Node3 node3) {
        try {
            node3Repository.save(node3);
        }catch (Exception e){
            System.out.println(e);
            return new Node3(302L, "fails to add node3");
        }
        return new Node3(200L, "add node3 successfully, " + "its message: " + node3.getMessage());
    }

    //delete
    public Node3 deleteNode3ById(Long id){
        Node3 n = checkById(id);
        if(n.getId() == 304L)
            return new Node3(303L, "fails to delete node3");

        try {
            node3Repository.delete(n);
            return new Node3(200L, "delete node3 successfully, " + "its id: " + n.getId());
        }catch (Exception e){
            return new Node3(303L, "fails to delete node3");
        }
    }

    //check id
    public Node3 checkById(Long id){
        List<Node3> list = node3Repository.findAllById(Collections.singleton(id));
        if (list.isEmpty()) {
            Node3 node3 = new Node3(304L, "node3 with id = "+id+" doesn't exist");
            list.add(node3);
        }
        return list.get(0);
    }
}
