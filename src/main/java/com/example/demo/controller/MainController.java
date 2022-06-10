package com.example.demo.controller;

import com.example.demo.Node1DB.Node1;
import com.example.demo.Node2DB.Node2;
import com.example.demo.Node3DB.Node3;
import com.example.demo.service.Node1Service;
import com.example.demo.service.Node2Service;
import com.example.demo.service.Node3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

@RestController
public class MainController {
    @Autowired
    Node1Service node1Service;

    @Autowired
    Node2Service node2Service;

    @Autowired
    Node3Service node3Service;

    String f1 = "/Users/wangyangxu/IdeaProjects/223project/src/main/resources/log1.txt";
    String f2 = "/Users/wangyangxu/IdeaProjects/223project/src/main/resources/log2.txt";
    String f3 = "/Users/wangyangxu/IdeaProjects/223project/src/main/resources/log3.txt";

    @RequestMapping(value = "/read")
    public Node1 getLatestMessage(){
        Node1 n1= node1Service.getNode1LatestMessage();
        return n1;
    }

    //check commit, if so, send replica log to node2 and node3
    @RequestMapping(value = "commit")
    public String node1Commit() {
            List<String> list = new ArrayList<>();
            if(logLength(f1) > logLength(f2) || logLength(f1) > logLength(f3))
                return "ABORT";
            try {
                File log1 = new File(f1);
                Scanner myReader = new Scanner(log1);
                while (myReader.hasNextLine()) {
                    String data = myReader.nextLine();
                    list.add(data);
                }
                myReader.close();
            } catch (FileNotFoundException e) {
                System.out.println("failed to read log.");
                e.printStackTrace();
            }
            if(list.isEmpty())
                return "ABORT";

            System.out.println(list);

            for (String s : list) {
                String[] arr = s.split(" ");
                System.out.println(arr[0]+" "+arr[1]+ " "+ arr[2]);
                if (arr[0].equals("ADD")) {
                    node2Service.addNode2(new Node2(arr[2]));
                    node3Service.addNode3(new Node3(arr[2]));
                } else if (arr[0].equals("DELETE")) {
                    node2Service.deleteNode2ById(Long.valueOf(arr[1]));
                    node3Service.deleteNode3ById(Long.valueOf(arr[1]));
                }
            }

            return "COMMIT";
    }

    // return the length of log
    public int logLength(String filename){
        int i = 0;
        try {
            File log = new File(filename);
            Scanner myReader = new Scanner(log);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if(!data.startsWith("READ"))
                    i++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return i;
    }
}
