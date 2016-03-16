
import bst.Node;
import bst.NodeM;
import bst.Tree;
import java.util.HashMap;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author mustafakirimli
 */
public class NodeOTest {
    private Tree tree;
    private HashMap nodes;
    
    /**
     * Creates tree showing below:
     *<pre> 
     *        55 
     *      /    \
     *     20     65
     *    /  \      \
     *   3   45     134 
     *  /   /  \
     * 2   40   48
     * </pre>
     */
    @Before
    public void setUp(){
        this.nodes = new HashMap<Integer, Node>();
        this.nodes.put(20,  new Node(20));
        this.nodes.put(65,  new Node(65));
        this.nodes.put(134, new Node(134));
        this.nodes.put(3,   new Node(3));
        this.nodes.put(2,   new Node(2));
        this.nodes.put(45,  new Node(45));
        this.nodes.put(40,  new Node(40));
        this.nodes.put(48,  new Node(48));
        // this node not in tre tree
        this.nodes.put(21,  new Node(21));
        
        tree = new Tree(55);
        NodeM.add(tree, (Node)nodes.get(20));
        NodeM.add(tree, (Node)nodes.get(65));
        NodeM.add(tree, (Node)nodes.get(134));
        NodeM.add(tree, (Node)nodes.get(3));
        NodeM.add(tree, (Node)nodes.get(2));
        NodeM.add(tree, (Node)nodes.get(45));
        NodeM.add(tree, (Node)nodes.get(40));
        NodeM.add(tree, (Node)nodes.get(48));
    }

    /**
     * Delete (set null) tree
     */
    @After
    public void tearDown(){
        this.tree = null;
        this.nodes = null;
    }
    
    @Test
    public void dummyTest(){
        Assert.assertTrue(true);
    }
}
