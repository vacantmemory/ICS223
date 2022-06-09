package com.example.demo.service;

import com.example.demo.Node1DB.Node1;
import com.example.demo.Node2DB.Node2;
import com.example.demo.Node2DB.Node2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@Service("Node2Service")
@Transactional("node2TransactionManager")
public class Node2Service {

    @Autowired
    Node2Repository node2Repository;

    String filename = "/Users/wangyangxu/IdeaProjects/223project/src/main/resources/log2.txt";

    //get latest message
    public Node2 getNode2LatestMessage(){
        Node2 n2 = node2Repository.findFirstByOrderByIdDesc();

        if(n2.getId() < 300L){
            try {
                FileWriter myWriter = new FileWriter(filename, true); //the true will append the new data
                myWriter.write("READ "+n2.getId()+" "+n2.getMessage()+"\n");//appends the string to the file
                myWriter.close();
                System.out.println("Node2 accept READ request");
            } catch (
                    IOException e) {
                System.out.println("Read Error occurred in Node2.");
                e.printStackTrace();
            }
        }

        return n2;
    }

    //check all data
    public List<Node2> getNode2DatabaseData() {
        List<Node2> list = node2Repository.findAll();
        return list;
    }

    //query
    public Node2 getNode2ById(Long id) {
        List<Node2> list = node2Repository.findAllById(Collections.singleton(id));
        if (list.isEmpty())
            return new Node2(301L, "node2 not found by id.");
        return list.get(0);
    }

    //add
    public Node2 addNode2(Node2 node2) {
        try {
            node2Repository.save(node2);
        }catch (Exception e){
            System.out.println(e);
            return new Node2(302L, "fails to add node2");
        }
        if(node2.getId() < 300L){
            try {
                FileWriter myWriter = new FileWriter(filename,true);
                myWriter.write("ADD "+node2.getId()+" "+node2.getMessage()+"\n");
                myWriter.close();
                System.out.println("Node2 accept ADD request");
            } catch (
                    IOException e) {
                System.out.println("ADD Error occurred.");
                e.printStackTrace();
            }
        }

        return new Node2(200L, "add node2 successfully, " + "its message: " + node2.getMessage());
    }

    //delete
    public Node2 deleteNode2ById(Long id){
        Node2 n = checkById(id);
        if(n.getId() == 304L)
            return new Node2(303L, "fails to delete node2");

        try {
            node2Repository.delete(n);
        }catch (Exception e){
            return new Node2(303L, "fails to delete node2");
        }

        if(n.getId() < 300L){
            try {
                FileWriter myWriter = new FileWriter(filename,true);
                myWriter.write("DELETE "+id+" "+n.getMessage()+"\n");
                myWriter.close();
                System.out.println("Node2 accept DELETE request");
            } catch (
                    IOException e) {
                System.out.println("DELETE Error occurred.");
                e.printStackTrace();
            }
        }

        return new Node2(200L, "delete node2 successfully, " + "its id: " + n.getId());
    }

    //check id
    public Node2 checkById(Long id){
        List<Node2> list = node2Repository.findAllById(Collections.singleton(id));
        if (list.isEmpty()) {
            Node2 Node2 = new Node2(304L, "node2 with id = "+id+" doesn't exist");
            list.add(Node2);
        }
        return list.get(0);
    }
}
