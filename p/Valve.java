package p;

import java.util.ArrayList;

public class Valve {
    private int flowRate;
    private ArrayList<Valve> connections;
    private String name;
    public Valve(String name) {
        flowRate=-1;
        connections = new ArrayList<>();
        this.name = name;
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
    public void addConnections(Valve newConnection) {
        connections.add(newConnection);
    }
    public String getName() {
        return name;
    }
}
