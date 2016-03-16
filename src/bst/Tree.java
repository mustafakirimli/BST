package bst;

/**
 * This class hold a Node
 * object as a Tree. 
 * 
 * We're using this class for 
 * flexibility on changing 
 * root node.
 * 
 * @author mustafakirimli
 */
public class Tree{
    // the root Node
    private Node root;

    /**
     * Initialize Tree with
     * integer value. 
     * @param value root node's value
     */
    public Tree(int value) {
        this.root = new Node(value);
    }
    
    /**
     * Initialize Tree with
     * Node object. 
     * @param node root's node 
     */
    public Tree(Node node) {
        this.root = node;
    }
    
    public Node getRoot(){
        return this.root;
    }
    
    public void setRoot(Node root) {
        this.root = root;
    }
    
    public void setRoot(int value){
        this.root = new Node(value);
    }

    public int getValue() {
        return root.getValue();
    }

    public Node getLeft() {
        return root.getLeft();
    }

    public void setLeft(Node left) {
        root.setLeft(left);
    }

    public Node getRight() {
        return root.getRight();
    }

    public void setRight(Node right) {
        root.setRight(right);
    }
}