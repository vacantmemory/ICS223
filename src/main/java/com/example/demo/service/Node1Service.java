package com.example.demo.service;

import com.example.demo.Node1DB.Node1;
import com.example.demo.Node1DB.Node1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

@Service("Node1Service")
@Transactional("node1TransactionManager")
public class Node1Service {
    @PersistenceContext
    EntityManager em;

    @Autowired
    Node1Repository node1Repository;

    String filename = "/Users/wangyangxu/IdeaProjects/223project/src/main/resources/log1.txt";

    //get latest message
    public Node1 getNode1LatestMessage(){
        Node1 n1 = node1Repository.findFirstByOrderByIdDesc();
        if(n1 == null)
            n1 = new Node1(300L,"Read Error, no data in node1");

        if(n1.getId() < 300L){
            try {
                FileWriter myWriter = new FileWriter(filename, true); //the true will append the new data
                myWriter.write("READ "+n1.getId()+" "+n1.getMessage()+"\n");//appends the string to the file
                myWriter.close();
                System.out.println("Node1 accept READ request");
            } catch (
                    IOException e) {
                System.out.println("Read Error occurred.");
                e.printStackTrace();
            }
        }
        return n1;
    }

    //query all data
    public List<Node1> getNode1DatabaseData() {
        List<Node1> list = node1Repository.findAll();
        return list;
    }

    //query
    public Node1 getNode1ById(Long id) {
        List<Node1> list = node1Repository.findAllById(Collections.singleton(id));
        if (list.isEmpty())
            return new Node1(301L, "node1 not found by id.");
        return list.get(0);
    }

    //add
    public Node1 addNode1(Node1 node1) {
        try {
            em.persist(node1);
        }catch (Exception e){
            return new Node1(302L, "fails to add node1");
        }

        if(node1.getId() < 300L){
            try {
                FileWriter myWriter = new FileWriter(filename,true);
                myWriter.write("ADD "+node1.getId()+" "+node1.getMessage()+"\n");
                myWriter.close();
                System.out.println("Node1 accept ADD request");
            } catch (IOException e) {
                System.out.println("ADD Error occurred.");
                e.printStackTrace();
            }
        }

        return new Node1(200L, "add node1 successfully, " + "its message: " + node1.getMessage());
    }

    //delete
    public Node1 deleteNode1ById(Long id){
        Node1 n = checkById(id);
        if(n.getId() == 304L)
            return new Node1(303L, "fails to delete node1");

        try {
            em.remove(n);
        }catch (Exception e){
            return new Node1(303L, "fails to delete node1");
        }

        if(n.getId() < 300L){
            try {
                FileWriter myWriter = new FileWriter(filename,true);
                myWriter.write("DELETE "+id+" "+n.getMessage()+"\n");
                myWriter.close();
                System.out.println("Node1 accept DELETE request");
            } catch (
                    IOException e) {
                System.out.println("DELETE Error occurred.");
                e.printStackTrace();
            }
        }

        return new Node1(200L, "delete node1 successfully, " + "its id: " + n.getId());
    }

    //check id
    public Node1 checkById(Long id){
        // JPQL query:
        TypedQuery<Node1> query = em.createQuery("SELECT n FROM Node1 n WHERE n.id = :i", Node1.class);
        query.setParameter("i", id);
        List<Node1> list = query.getResultList();
        if (list.isEmpty()) {
            Node1 node1 = new Node1(304L, "Node1 with id = "+id+" doesn't exist");
            list.add(node1);
        }
        return list.get(0);
    }




}
