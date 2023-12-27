package day8;

public class Node {

    private String node;
    private String leftDirection;
    private String rightDirection;
    private int index;

    public Node(String node, String leftDirection, String rightDirection, int index) {
        this.node = node;
        this.leftDirection = leftDirection;
        this.rightDirection = rightDirection;
        this.index = index ;
    }

    public String getNode() {
        return node;
    }

    public String getLeftDirection() {
        return leftDirection;
    }

    public String getRightDirection() {
        return rightDirection;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        return "Node{" +
                "node='" + node + '\'' +
                ", leftDirection='" + leftDirection + '\'' +
                ", rightDirection='" + rightDirection + '\'' +
                ", index=" + index +
                '}';
    }
}
