import java.util.ArrayList;

public class Node {
    String value;
    ArrayList<Node> connections;
    boolean isLower;

    public Node(String v) {
        this.value = v;
        this.connections = new ArrayList<Node>();
        this.isLower = !Character.isUpperCase(v.charAt(0));
    }

    public String toString() {
        String conns = "";
        for (Node node : this.connections) {
            conns += " "+node.value+",";
        }
        return ""+this.value+":"+conns;
    }

    public void addConnection(Node n) {
        connections.add(n);
    }
}
