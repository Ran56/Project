package binaryTree;

public class Dfs {
    static class Node
    {
        Node left;
        Node right;
        int value;
        Node(int value)
        {
          this.value = value;
        }
    }

    public static void preOrder(Node node)
    {
        if(node!=null)
        {
            System.out.println(node.value);
        }
        else
            {
                return;
            }
        if(node.left!=null) preOrder(node.left);
        if(node.right!=null) preOrder(node.right);
    }


    public static void main(String[] args) {
        Node root = new Node(5);
        Node left = new Node(4);
        Node right = new Node(1);
        root.left = left;
        root.right = right;
        left.left = new Node(3);
        left.right = new Node(2);
        right.right=new Node(2);
        preOrder(root);

    }


}
