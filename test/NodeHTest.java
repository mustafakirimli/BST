
import bst.Node;
import bst.NodeH;
import bst.NodeM;
import bst.Tree;
import java.util.List;
import java.util.Random;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author mustafakirimli
 */
public class NodeHTest {
    private int[] n;
    private Tree tree;
            
    /**
     * Creates tree showing below:
     *<pre> 
     *        55 
     *      /    \
     *     20     65
     *    /  \      \
     *   3   45     134 
     *  /
     * 2
     * </pre>
     */
    @Before
    public void setUp(){
        Random rand = new Random();
        n = new int[]{
            rand.nextInt(Integer.MAX_VALUE) + 1, 
            rand.nextInt(Integer.MAX_VALUE) + 1, 
            rand.nextInt(Integer.MAX_VALUE) + 1
        };
        
        tree = new Tree(new Node(55));
        NodeM.add(tree, new Node(20));
        NodeM.add(tree, new Node(65));
        NodeM.add(tree, new Node(65));
        NodeM.add(tree, new Node(134));
        NodeM.add(tree, new Node(3));
        NodeM.add(tree, new Node(2));
        NodeM.add(tree, new Node(45));
    }

    /**
     * Delete (set null) tree
     */
    @After
    public void tearDown(){
        this.n = null;
        this.tree = null;
    }
    
    /**
     * Test the "first" method for finding 
     * first (lowest) element's value.
     */
    @Test
    public void findFirstElement() {
        Assert.assertEquals(
                "Find first element's value",
                2, 
                NodeH.first(this.tree)
        );
        
        Assert.assertEquals(
                "Find first element",
                2,
                NodeH.First(tree.getRoot()).getValue()
        );
    }
    
    /**
     * Test the "first" method for finding 
     * first (lowest) element's value with leaf.
     */
    @Test
    public void firstElementWithLeaf() {
        Node root = new Node(n[0]);
        Assert.assertEquals(
                "Find first (lowest) element",
                n[0], 
                NodeH.first(root)
        );
        
        Assert.assertEquals(
                "Find first element",
                n[0],
                NodeH.First(root).getValue()
        );
    }
    
     /**
     * Test the "last" method for finding 
     * last (highest) element's value.
     */
    @Test
    public void findLastElement() {
        Assert.assertEquals(
                "Find last element' value",
                134,
                NodeH.last(this.tree)
        );
        
        Assert.assertEquals(
                "Find last element",
               134,
                NodeH.Last(tree.getRoot()).getValue()
        );
    }
    
    /**
     * Test the "last" method for finding 
     * last (highest) element's value with leaf.
     */
    @Test
    public void lastElementWithLeaf() {
        Node root = new Node(n[0]);
        Assert.assertEquals(
                "Find last (highest) element",
                n[0],
                NodeH.last(root)
        );
        
        Assert.assertEquals(
                "Find last element",
                n[0],
                NodeH.Last(root).getValue()
        );
    }
    
    /**
     * Test the "contains element" function on
     * left side. Because the program may have 
     * problem on searching on left side, 
     * while right side working as well.
     */
    @Test
    public void containsElementOnLeftSide() {
        Assert.assertTrue(
                "Search given (left side) element",
                NodeH.contains(tree.getRoot(), new Node(20))
        );
    }
    
    /**
     * Test the "contains element" function on
     * right side. Because the program may have 
     * problem on searching on right side, 
     * while left side working as well.
     */
    @Test
    public void containsElementOnRightSide() {
        Assert.assertTrue(
                "Search given (right side) element",
                NodeH.contains(tree.getRoot(), new Node(134))
        );
    }
    
    /**
     * Test the "contains element" function
     * on tree with not exists element.
     * We're expecting the function couldn't 
     * find it.
     */
    @Test
    public void containsElementNotExists(){
        Assert.assertFalse(
                "search not exists element",
                NodeH.contains(tree.getRoot(), new Node(140))
        );
    }
    
    /**
     * Test the subSet function following cases; 
     * - is it returns array or not,
     * - is it returns Node of array or not,
     * - is it return expected result or not
     */
    @Test
    public void testSubSet() {
        List<Node> nodes 
                = NodeH.subSet(tree.getRoot(), 20, 65);
        
        Assert.assertEquals("",
                55, 
                nodes.get(0).getValue()
        );
        
        Assert.assertEquals(
                "",
                20, 
                nodes.get(1).getValue()
        );
        
        Assert.assertEquals(
                "",
                45, 
                nodes.get(2).getValue()
        );
        
        Assert.assertEquals(
                "",
                3, 
                nodes.size()
        );
    }
    
    /**
     * Test the subSet function 
     * with range tree not have.
     */
    @Test
    public void testSubSetOutOfRange() {
        List<Node> nodes 
                = NodeH.subSet(tree.getRoot(), 200, 400);
        
        Assert.assertEquals(
                "",
                0, 
                nodes.size()
        );
        
        List<Node> nodes2 
                = NodeH.subSet(tree.getRoot(), 20, 20);
        
        /* this test doesn't return anything,
           beacause our subSet method's "from"
           parameter inclusive, "to" parameter
           exclusive (from >= x < to). 
           When called subSet method like below,
           the method compares value like that;
           "20 >= 20 < 20" and returns false.
        */
        Assert.assertEquals(
                "",
                0, 
                nodes2.size()
        );
    }
    
    /**
     * Tests isSearchTree function. 
     */
    @Test
    public void isSearchTree() {
        Assert.assertTrue(
                "Check is it Binary Search Tree",
                NodeH.isSearchTree(tree.getRoot())
        ); 
    }
 
    /**
     * Test isSearchTree function 
     * with BT (not BTS)
     */
    @Test
    public void isSearchTreeNot() {
       Node btree = new Node(55);
       btree.setLeft(new Node(65));
       btree.setRight(new Node(20));
        
        Assert.assertFalse(
                "Check is not Binary Search Tree",
                NodeH.isSearchTree(btree)
        ); 
    }
    
    /**
     * Tests isInRange method.
     */
    @Test
    public void isInRange(){
        Node node = new Node(50);
        Assert.assertTrue(
                "Check is is range",
                NodeH.isInRange(node, 49, 51)
        );
        
        Assert.assertFalse(
                "Check is not range",
                NodeH.isInRange(node, 50, 50)
        );
    }
    
    /**
     * Tests get element.
     */
    @Test
    public void testGet(){
        Assert.assertEquals(
            "Get given node and compare value",
            134,
            NodeH.get(tree.getRoot(), 134).getValue()
        );
        
        Assert.assertNull(
                "try to get not exists node",
                NodeH.get(tree.getRoot(), 500)
        );
    }
    
    /**
     * Tests get method with 
     * not exists node.
     */
    @Test
    public void testGetNotExists(){
        Assert.assertNull(
                "try to get not exists node",
                NodeH.get(tree.getRoot(), 500)
        );
    }
    
    @Test
    public void testIsLeaf(){
        Assert.assertEquals(
            "",
            NodeH.NodeType.LEAF_NODE,
            NodeH.nodeType(tree.getRight().getRight())
        );
        
        Assert.assertNotEquals(
                "",
                NodeH.NodeType.LEAF_NODE,
                NodeH.nodeType(tree.getLeft())
        );
    }
    
    @Test
    public void testIsLeafWithNodeType(){
        Assert.assertEquals(
            "",
            NodeH.NodeType.LEAF_NODE,
            NodeH.nodeType(tree.getRight().getRight())
        );
        
        Assert.assertNotEquals(
                "",
                NodeH.NodeType.LEAF_NODE,
                NodeH.nodeType(tree.getLeft())
        );
    }
    
    @Test
    public void testHasOnlyOneChild(){
        Assert.assertEquals(
                "", 
                NodeH.NodeType.HAS_ONLY_ONE_CHILD,
                NodeH.nodeType(tree.getRight())
        );
    }
    
    @Test
    public void testHasOnlyOneChildWithNodeType(){
        Assert.assertEquals(
                "", 
                NodeH.NodeType.HAS_ONLY_ONE_CHILD,
                NodeH.nodeType(tree.getRight())
        );
    }
    
    @Test
    public void testParentNode(){
        Assert.assertEquals(
                "Find node's parent which is value 20",
                55,
                NodeH.parent(tree.getRoot(), 20).getValue()
        );
        
        Assert.assertNotEquals(
                "Find node's parent which is value 2",
                20,
                NodeH.parent(tree.getRoot(), 2).getValue()
        );
        
        Assert.assertNotEquals(
                "Find node's parent with Node el",
                20,
                NodeH.parent(tree.getRoot(), new Node(2)).getValue()
        );
    }
}
