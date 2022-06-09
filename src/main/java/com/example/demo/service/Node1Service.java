package com.example.demo.service;

import com.example.demo.Node1DB.Node1;
import com.example.demo.Node1DB.Node1Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Service("Node1Service")
@Transactional("node1TransactionManager")
public class Node1Service {
    @PersistenceContext
    EntityManager em;

    @Autowired
    Node1Repository node1Repository;

    //query
    public List<Node1> getNode1ById(Long id) {
        List<Node1> list = node1Repository.findAllById(Collections.singleton(id));
        if (list.isEmpty()) {
            Node1 node1 = new Node1(301L, "node1 not found by id.");
            list.add(node1);
        }
        return list;
    }

    //add
    public Node1 addNode1(Node1 node1) {
        try {
            em.persist(node1);
        }catch (Exception e){
            return new Node1(302L, "fails to add node1");
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
            return new Node1(200L, "delete node1 successfully, " + "its id: " + n.getId());
        }catch (Exception e){
            return new Node1(303L, "fails to delete node1");
        }
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
