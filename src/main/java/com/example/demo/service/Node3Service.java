package com.example.demo.service;

import com.example.demo.Node1DB.Node1;
import com.example.demo.Node2DB.Node2;
import com.example.demo.Node3DB.Node3;
import com.example.demo.Node3DB.Node3Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service("Node3Service")
@Transactional("node3TransactionManager")
public class Node3Service {

    @Autowired
    Node3Repository node3Repository;

    String filename = "/Users/wangyangxu/IdeaProjects/223project/src/main/resources/log3.txt";

    //get latest message
    public Node3 getNode2LatestMessage(){
        Node3 n3 = node3Repository.findFirstByOrderByIdDesc();

        if(n3.getId() < 300L){
            try {
                FileWriter myWriter = new FileWriter(filename, true); //the true will append the new data
                myWriter.write("READ "+n3.getId()+" "+n3.getMessage()+"\n");//appends the string to the file
                myWriter.close();
                System.out.println("Node3 accept READ request");
            } catch (
                    IOException e) {
                System.out.println("Read Error occurred in Node3.");
                e.printStackTrace();
            }
        }

        return n3;
    }

    //check all datas
    public List<Node3> getNode3DatabaseData() {
        List<Node3> list = node3Repository.findAll();
        return list;
    }

    //query
    public Node3 getNode3ById(Long id) {
        List<Node3> list = node3Repository.findAllById(Collections.singleton(id));
        if (list.isEmpty())
            return new Node3(301L, "node3 not found by id.");
        return list.get(0);
    }

    //add
    public Node3 addNode3(Node3 node3) {
        try {
            node3Repository.save(node3);
        }catch (Exception e){
            System.out.println(e);
            return new Node3(302L, "fails to add node3");
        }

        if(node3.getId() < 300L){
            try {
                FileWriter myWriter = new FileWriter(filename,true);
                myWriter.write("ADD "+node3.getId()+" "+node3.getMessage()+"\n");
                myWriter.close();
                System.out.println("Node3 accept ADD request");
            } catch (
                    IOException e) {
                System.out.println("ADD Error occurred.");
                e.printStackTrace();
            }
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
        }catch (Exception e){
            return new Node3(303L, "fails to delete node3");
        }

        if(n.getId() < 300L){
            try {
                FileWriter myWriter = new FileWriter(filename,true);
                myWriter.write("DELETE "+id+" "+n.getMessage()+"\n");
                myWriter.close();
                System.out.println("Node3 accept DELETE request");
            } catch (
                    IOException e) {
                System.out.println("DELETE Error occurred.");
                e.printStackTrace();
            }
        }

        return new Node3(200L, "delete node3 successfully, " + "its id: " + n.getId());
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
