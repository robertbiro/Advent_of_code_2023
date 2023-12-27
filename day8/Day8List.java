package day8;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day8List {
    static int indexOfDirection = 0;
    static int indexOfNodes = 0;
    static String nextNode = "";
    static long step = 0;
    static String node = "";

    public static void readFile(String path) {

        List<Node> nodes = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(path);
            Scanner sc = new Scanner(fis);
            String firstLine = sc.nextLine();
            char[] direction = firstLine.toCharArray();
            int nodeIndex = 1;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (!line.isEmpty()) {
                    String[] nodeAsArray = line.replace("=", ",").replace("(", "")
                            .replace(")", "").replace(" ", "").split(",");
                    //System.out.println(Arrays.toString(nodeAsArray));
                    String node = nodeAsArray[0];
                    String leftDirection = nodeAsArray[1];
                    String rightDirection = nodeAsArray[2];
                    nodes.add(new Node(node, leftDirection, rightDirection, nodeIndex));
                    nodeIndex++;


                }
            }
            for (Node node : nodes) {
                //System.out.println(node.toString());
            }
            while (!node.equals("ZZZ")) {
                nextNode = findNextNodeByDirectionCollection(direction, nodes);
            }
            System.out.println(step);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String findNextNodeByDirectionCollection(char[] direction, List<Node> nodes) {
        if (indexOfDirection >= direction.length) {
            indexOfDirection = indexOfDirection - direction.length;
        }
        char dir = direction[indexOfDirection];
        if (dir == 'L') {
            nextNode = nodes.get(indexOfNodes).getLeftDirection();
        } else {
            nextNode = nodes.get(indexOfNodes).getRightDirection();
        }
        findCurrentNode(nodes);
        indexOfDirection++;
        return nextNode;
    }

    public static int findCurrentNode(List<Node> nodes) {
        String currentNode = "";
        while (!currentNode.equals(nextNode)) {
            currentNode = nodes.get(indexOfNodes).getNode();
            if (currentNode.equals(nextNode)) {
                step++;
                node = currentNode;
                //System.out.println(step);
                System.out.println("Step: " + step + ", Node: " + node);
                break;
            }
            indexOfNodes++;
            if (indexOfNodes >= nodes.size()) {
                indexOfNodes = indexOfNodes - nodes.size();
            }
        }
        return indexOfNodes;
    }




    public static void main(String[] args) {
        String path = "day8/eight.txt";
        readFile(path);
    }

}
