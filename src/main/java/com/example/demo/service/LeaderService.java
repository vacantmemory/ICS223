package com.example.demo.service;

import com.example.demo.Node1DB.node1;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Component
@Transactional("node1TransactionManager")
public class LeaderService {
    @PersistenceContext
    EntityManager em;

    public node1 getNode1ByMessage(String message) {
        // JPQL query:
        TypedQuery<node1> query = em.createQuery("SELECT n FROM node1 n WHERE n.message = :m", node1.class);
        query.setParameter("m", message);
        List<node1> list = query.getResultList();
        if (list.isEmpty()) {
            throw new RuntimeException("node1 not found by message.");
        }
        return list.get(0);
    }

    public node1 addNode1(node1 node1) {
        try {
            em.persist(node1);
        }catch (Exception e){
            return new node1(301L, "fails to add node1");
        }
        return new node1(200L, "add node1 successfully " + "its message:" + node1.getMessage());
    }





}
