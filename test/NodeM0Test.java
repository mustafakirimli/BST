
import bst.Node;
import bst.NodeM;
import bst.Tree;
import static org.hamcrest.CoreMatchers.instanceOf;
import org.junit.Assert;
import org.junit.Test;


/**
 *
 * @author mustafakirimli
 */
public class NodeM0Test {
    
    /**
     * When adding a new node, if the new 
     * node's value bigger than parent's value, 
     * the new value have to be added to Right 
     * side. And we're checking bot left and 
     * right side for checking adding process. 
     */
    @Test
    public void addNodeToRight(){
        Tree tree = new Tree(55);
        NodeM.add(tree, new Node(65));
        
        Assert.assertThat(
                "Check right node instance",
                tree.getRight(), 
                instanceOf(Node.class)
        );
        Assert.assertNull(
                "Check left node is empty or not",
                tree.getLeft()
        );
        Assert.assertEquals(
                "Check right node value",
                65, 
                tree.getRight().getValue()
        );
    }
    
    /**
     * When adding a new node, if the new 
     * node's value smaller than parent's value, 
     * the new value have to be added to Left 
     * side. And we're checking bot left and
     * right side for checking adding process. 
     */
    @Test
    public void testAddNodeToLeft(){
        Tree tree = new Tree(55);
        NodeM.add(tree, new Node(20));
        
        Assert.assertThat(
                "",
                tree.getLeft(), 
                instanceOf(Node.class)
        );
        Assert.assertNull(
                tree.getRight()
        );
        Assert.assertEquals(
                20, 
                tree.getLeft().getValue()
        );
    }
    
    /**
     * When adding a new node to tree,
     * we have to ignore duplicate values.
     * Because of that the program has to 
     * handle equality between new node's 
     * value and parent node's value.
     */
    @Test
    public void testAddNodeDuplicate() {
        Tree tree = new Tree(55);
        NodeM.add(tree, new Node(65));
        NodeM.add(tree, new Node(65));
        
        Assert.assertNull(
                "",
                tree.getLeft()
        );
        Assert.assertNull(
                "",
                tree.getRight().getLeft()
        );
    }
}
