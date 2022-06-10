package ICS223.demo.service;

import ICS223.demo.database.Node;
import ICS223.demo.database.NodeRepository;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;

@RestController
public class ClientServiceController {

    private Logger logger = LoggerFactory.getLogger(ClientServiceController.class);

    @Autowired
    NodeRepository nodeRepository;

    @RequestMapping(method = RequestMethod.GET, path = "/message/{id}")
    public Node retrieveMessage(@PathVariable long id) {
        logger.info("retrieveMessage called with {}", id);

        Node node = nodeRepository.findFirstById(id);
        if(node==null){
            throw new RuntimeException("Unable to find data for " + id);
        }

        return node;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/messagelatest")
    public Node retrieveLatestMessage() {
        logger.info("retrieveLatestMessage called");
        Node node = nodeRepository.findFirstByOrderByIdDesc();
        if(node==null){
            throw new RuntimeException("Unable to find latest message.");
        }

        return node;
    }

    // TODO: Add the communication between three peers.
    @RequestMapping(method = RequestMethod.POST, path = "/messages")
    public Node addMessage(@Valid @RequestBody Node node) {
        Node savedNode = nodeRepository.save(node);
        return savedNode;
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/message/{id}")
    public void deleteUser(@PathVariable int id){
        nodeRepository.deleteById((long) id);
    }
}
