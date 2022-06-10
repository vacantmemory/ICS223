package ICS223.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
public class PeerServiceController {

    @Autowired
    private Environment environment;

    private boolean isLeader = false;
    private List<Integer> peer = new ArrayList<Integer>();
    private boolean peerListSet = false;
    private long minBallotNumber = 0;

    @RequestMapping(method = RequestMethod.GET, path = "/leader/status")
    public PeerService checkLeader() {
        PeerService peerService = new PeerService();
        peerService.setLeader(isLeader);
        return peerService;
    }

    @RequestMapping(method = RequestMethod.GET, path = "/leader/apply")
    public PeerService applyForLeader() {
        if (!peerListSet) {setPeer();}
        // TODO:
    }

    @RequestMapping(method = RequestMethod.POST, path = "/leader")
    public PeerService testforAPI() {
        if (!peerListSet) {setPeer();}
    }


    private void setPeer() {
        String port = environment.getProperty("local.server.port");
        if (port.equals("8080")) {
            peer.add(8081);
            peer.add(8082);
        } else if (port.equals("8081")) {
            peer.add(8080);
            peer.add(8082);
        } else {
            peer.add(8080);
            peer.add(8081);
        }
        peerListSet = true;
    }
}
