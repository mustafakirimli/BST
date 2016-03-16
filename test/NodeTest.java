
import bst.Node;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.After;
import org.junit.Before;


/**
 * Tests the Node object.
 * 
 * The test covers the create instance, 
 * set child and get values
 *
 * @author mustafakirimli
 */
public class NodeTest {
    
    private int[] n;
    
    /**
     * Before the every tests run, 
     * set a random integer array to n attribute.
     */
    @Before
    public void setUp(){
        Random rand = new Random();
        n = new int[]{
            rand.nextInt(Integer.MAX_VALUE) + 1, 
            rand.nextInt(Integer.MAX_VALUE) + 1, 
            rand.nextInt(Integer.MAX_VALUE) + 1
        };
    }

    /**
     * Set null value for n variable.
     */
    @After
    public void tearDown(){
        this.n = null;
    }
    
    /**
     * When we create a Node, node's 
     * value have to equals our value
     */
    @Test
    public void createdNodeValue(){
        Node node = new Node(n[0]);
        Assert.assertEquals(
                "Is node's value equals given?", 
                n[0], 
                node.getValue()
        );
    }
    
    /**
     * When we've created root node, 
     * the left and right child have to be empty (null)
     */
    @Test
    public void createdNodesEmptyChild() {
        Node node = new Node(n[0]);
        Assert.assertNull(
                "Left child have to be null", 
                node.getLeft()
        );
        Assert.assertNull(
                "Right child have to be null", 
                node.getRight()
        );
    }
    
    /**
     * When we set a left (child) node, 
     * getLeft method have to return instance of Node
     */
    @Test
    public void leftChildInstance(){
        Node root = new Node(n[0]);
        Node left = new Node(n[1]);
        root.setLeft(left);
        
        Assert.assertThat(
                "Left child have to be instance of Node",
                root.getLeft(), 
                instanceOf(Node.class)
        );
    }
    
    /**
     * When we set a right (child) node, 
     * getRight method have to return instance of Node
     */
    @Test
    public void rightChildInstance(){
        Node root = new Node(n[0]);
        Node right = new Node(n[1]);
        root.setRight(right);
        
        Assert.assertThat(
                "Right child have to be instance of Node",
                root.getRight(), 
                instanceOf(Node.class)
        );
    }
    
    /**
     * When we set a left node, 
     * right child has to be null
     */
    @Test
    public void leftChildNodeCheckRight(){
        Node root = new Node(n[0]);
        Node left = new Node(n[1]);
        root.setLeft(left);
        Assert.assertNull(
                "Right child have to be null",
                root.getRight()
        );
    }
    
    /**
     * When we set a right node, 
     * left child has to be null
     */
    @Test
    public void testAddRightChildNodeCheckLeft(){
        Node root = new Node(n[0]);
        Node right = new Node(n[1]);
        root.setRight(right);
        Assert.assertNull(
                "Left child have to be null",
                root.getLeft()
        );
    }
    
    /**
     * When we set a left node the getLeft method 
     * have to return integer value as we set.
     */
    @Test
    public void testLeftChildValue(){
        Node root = new Node(n[0]);
        Node left = new Node(n[1]);
        root.setLeft(left);
        Assert.assertEquals(
                "Is left child value equals given?",
                n[1], 
                root.getLeft().getValue()
        );
    }
    
    /**
     * When we set a right node the getRight method 
     * have to return integer value as we set.
     */
    @Test
    public void testRightChildValue(){
        Node root = new Node(n[0]);
        Node right = new Node(n[1]);
        root.setRight(right);
        Assert.assertEquals(
                "Is right child value equals given?",
                n[1], 
                root.getRight().getValue()
        );
    }
}
