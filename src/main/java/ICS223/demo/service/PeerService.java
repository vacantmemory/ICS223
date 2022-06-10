package ICS223.demo.service;

import javax.persistence.Entity;

@Entity
public class PeerService {

    private boolean isLeader;

    private boolean isPrepared;

    private boolean isCommitted;

    private String message;

    private long ballotNumber;

    public PeerService() {
        super();
    }

    public PeerService(boolean isLeader, boolean isPrepared, boolean isCommitted, String message, long ballotNumber) {
        this.isLeader = isLeader;
        this.isPrepared = isPrepared;
        this.isCommitted = isCommitted;
        this.message = message;
        this.ballotNumber = ballotNumber;
    }

    public boolean isLeader() {
        return isLeader;
    }

    public void setLeader(boolean leader) {
        isLeader = leader;
    }

    public boolean isPrepared() {
        return isPrepared;
    }

    public void setPrepared(boolean prepared) {
        isPrepared = prepared;
    }

    public boolean isCommitted() {
        return isCommitted;
    }

    public void setCommitted(boolean committed) {
        isCommitted = committed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getBallotNumber() {
        return this.ballotNumber;
    }

    public void setBallotNumber(long ballotNumber) {
        this.ballotNumber = ballotNumber;
    }
}
