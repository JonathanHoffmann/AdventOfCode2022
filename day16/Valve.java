package day16;

import java.util.ArrayList;

public class Valve {
    private int flowRate;
    private ArrayList<Valve> connections;
    private String name;
    private String partners;
    private Boolean opened;
    private Valve bestMove;

    public String getPartners() {
        return partners;
    }

    public void setPartners(String partners) {
        this.partners = partners;
    }

    public Valve(String name) {
        flowRate = -1;
        connections = new ArrayList<>();
        this.name = name;
        partners = "";
        opened = false;
        bestMove = null;
    }

    public Boolean isOpened() {
        return opened;
    }

    public void open() {
        opened = true;
    }

    public int getFlowRate() {
        return flowRate;
    }

    public void setFlowRate(int flowRate) {
        this.flowRate = flowRate;
    }

    public ArrayList<Valve> getConnections() {
        return connections;
    }

    public void addConnection(Valve newConnection) {
        connections.add(newConnection);
    }

    public String getName() {
        return name;
    }

    public String calcBestMove(int depth) {
        
        return "hi";
    }
}
