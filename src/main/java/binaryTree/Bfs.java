package binaryTree;

import javax.management.Query;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Bfs {
    public static class Node
    {
        Node left;
        Node right;
        int value;
        Node(int value)
        {
            this.value = value;
        }
    }

    public static List<List<Integer>> largestValues(Node node)
    {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        queue.offer(node);
        while(!(queue.isEmpty()))
        {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for(int i =0;i<size;i++)
            {
                Node node1 = queue.poll();

            level.add(node1.value);
            if(!((node1.left) ==null))
            {
                queue.offer(node.left);
            }
            if(!((node1.right) ==null))
            {
                queue.offer(node1.right);
            }
                result.add(level);
            }
            System.out.println(result);
        }
        return result;
    }



    public static void main(String[] args){
        Bfs queueSolution = new Bfs();
        Node root = new Node(1);
        Node left = new Node(5);
        Node right = new Node(3);
        root.left = left;
        root.right = right;
        left.left = new Node(8);
        right.left= new Node(7);
        right.right=new Node(9);
       // largestValues(root);

    }
}
