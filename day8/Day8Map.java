package day8;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Day8Map {
    static int indexOfDirection = 0;
    static long step = 0;
    static String currentNode = "AAA";


    public static void readFile(String path) {

        Map<String, Node> nodeMap = new HashMap<>();
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
                    String nodeName = nodeAsArray[0];
                    String leftDirection = nodeAsArray[1];
                    String rightDirection = nodeAsArray[2];
                    Node node = new Node(nodeName, leftDirection, rightDirection, nodeIndex);
                    nodeMap.put(nodeName, node);
                    nodeIndex++;
                }
            }

            for (Map.Entry<String, Node>entry : nodeMap.entrySet()) {
                System.out.println(entry.getKey() + ":" + entry.getValue());
            }
            while (!currentNode.equals("ZZZ")) {
                String nextNode = findNextNodeByDirectionCollection(direction, nodeMap);
                findCurrentNode(nodeMap, nextNode);
            }
            System.out.println(step);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String findNextNodeByDirectionCollection(char[] direction, Map<String, Node> nodeMap) {
        String nextNode;
        if (indexOfDirection >= direction.length) {
            indexOfDirection = indexOfDirection - direction.length;
        }
        char dir = direction[indexOfDirection];
        if (dir == 'L') {
            nextNode = nodeMap.get(currentNode).getLeftDirection();
        } else {
            nextNode = nodeMap.get(currentNode).getRightDirection();
        }
        indexOfDirection++;
        return nextNode;
    }

    public static void findCurrentNode(Map<String, Node> nodeMap, String nextNode) {
        Node valuesOfNodeInMap = nodeMap.get(nextNode);  //to retrieve the value (type is node)
        step++;
        currentNode = valuesOfNodeInMap.getNode(); //to avoid the shadow needed the local variable - valuesOfNodeInMap
        System.out.println("Step: " + step + ", Node: " + currentNode);
    }

    public static void main(String[] args) {
        String path = "day8/eight.txt";
        readFile(path);
    }
}
