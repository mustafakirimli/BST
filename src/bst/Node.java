package bst;


/**
 * Simple Binary Tree node 
 * which is allows only integer value.
 * 
 * @author mustafakirimli
 */
public class Node {
    private final int value;
    private Node left, right;

    public Node(int value) {
        this.value = value;
        this.left = this.right = null;
    }
    
    public int getValue() {
        return value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
