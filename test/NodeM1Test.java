
import bst.Node;
import bst.NodeH;
import bst.NodeM;
import bst.Tree;
import java.lang.ref.WeakReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 *
 * @author mustafakirimli
 */
public class NodeM1Test {
    private Tree tree;
    
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
        tree = new Tree(55);
        NodeM.add(tree, new Node(20));
        NodeM.add(tree, new Node(65));
        NodeM.add(tree, new Node(134));
        NodeM.add(tree, new Node(3));
        NodeM.add(tree, new Node(2));
        NodeM.add(tree, new Node(45));
        NodeM.add(tree, new Node(40));
        NodeM.add(tree, new Node(48));
    }

    /**
     * Delete (set null) tree
     */
    @After
    public void tearDown(){
        this.tree = null;
    }
    
    @Test
    public void replaceChildNode() throws 
            NoSuchMethodException, 
            IllegalAccessException, 
            IllegalArgumentException, 
            InvocationTargetException{
        
        // Create a Weak Ref on obj
        WeakReference<Node> weakRef 
                  = new WeakReference<>(tree.getLeft());
        
        /* 
         find method and correct signiture
         because there can be a method 
         overloading
        */
        Method method = NodeM.class.getDeclaredMethod(
                // method name
                "replace", 
                // method parameter type for determine method signiture
                new Class[]{Node.class, Node.class, Node.class} 
        );
        
        // set accessible to true
        method.setAccessible(true);
        
        /*
         call method, and store result.
         We passed null for first parameter 
         because our method is static and
         we don't need to class instance.
        */
        Object result = method.invoke(
                null, 
                new Object[]{
                    tree.getRoot(), 
                    NodeH.get(tree, 20), 
                    new Node(21)
                }
        );
        
        /*
        run garbage collector, because
        we'll check deleted (replaced)
        node's memory reference, is removed
        or not.
        */
        System.gc();
        
        Assert.assertTrue(
                "Replace method have to return true",
                (Boolean)result
        );
        Assert.assertEquals(
                "Check replaced node's value",
                21, 
                tree.getLeft().getValue()
        );
        
        Assert.assertNull(
                "Old node has to be deleted from memory",
                weakRef.get()
        );
    }
    
    @Test
    public void replaceChild(){
        Tree tree2 = new Tree(200);
        
        NodeM.copyChild(tree.getLeft(), tree2.getRoot());
        
        Assert.assertEquals(
                "Check copied child left node",
                3,
                tree2.getLeft().getValue()
        );
        
        Assert.assertEquals(
                "Check copied child right node",
                45,
                tree2.getRight().getValue()
        );
    }
    
    /**
     * Test deleting leaf (which node 
     * does not have any child node)
     */
    @Test
    public void deleteLeafNode() {
        // Create a Weak Ref on obj
        WeakReference<Node> weakRef 
                  = new WeakReference<>(NodeH.get(tree, 134));
        
        NodeM.delete(tree, 134);
        
        // Keep your fingers crossed    
        System.gc(); 
        
        Assert.assertNull(
                "",
                weakRef.get()
        );
        
        Assert.assertNull(
                "Check deleting leaf",
                NodeH.get(tree, 134)
        );
    }
    
    @Test
    public void deleteOneChildNode() {
        
        Node n3 = NodeH.get(tree, 3);
        
        Assert.assertNotEquals(
                "Node 3 has left child",
                NodeH.NodeType.LEAF_NODE,
                NodeH.nodeType(NodeH.get(tree, 3))
        );
        NodeM.delete(tree, 3);
        Assert.assertNull(
                "Check deleting one child node",
                NodeH.get(tree, 3)
        );
        Assert.assertEquals(
                "Check new node's left node",
                2,
                NodeH.get(tree, 20).getLeft().getValue()
        );
        Assert.assertEquals(
                "Node 3 has to be leaf after deleting",
                NodeH.NodeType.LEAF_NODE,
                NodeH.nodeType(n3)
        );
    }
    
    @Test
    public void deleteLeftAndRightChildNode(){
        
        NodeM.delete(tree, 20);
        
        Assert.assertNull(
                "",
                NodeH.get(tree, 45).getLeft()
        );
        
        Assert.assertEquals(
            "",
            40,
            tree.getLeft().getValue()
        );
        
        Assert.assertEquals(
            "",
            3,
            tree.getLeft().getLeft().getValue()
        );
        Assert.assertEquals(
            "",
            45,
            tree.getLeft().getRight().getValue()
        );
    }
    
    @Test
    public void deleteRootNodeNoChild(){
        Tree tree2 = new Tree(55);
        
        NodeM.delete(tree2, null);
        
        // Delete root leaf (no child)
        Assert.assertNull(
                "",
                tree2.getRoot()
        );
    }
    
    @Test
    public void deleteRootNodeOneChild(){
        Tree tree2 = new Tree(55);
        tree2.getRoot().setLeft(new Node(20));
        
        NodeM.delete(tree2, null);
        
        // Delete root which has only one child
        Assert.assertEquals(
                "",
                20,
                tree2.getValue()
        );
    }
    
    @Test
    public void deleteRootNodeBothChild(){
        // Create a Weak Ref on obj
        WeakReference<Node> weakRef 
                  = new WeakReference<>(tree.getRoot());
        
        NodeM.delete(tree, null);
        
        // Keep your fingers crossed    
        System.gc();    

        // should be null if GC collected
        Assert.assertNull(
                "Deleted old root's node has to be deleted on memory",
                weakRef.get()
        );
        
        // Delete root node which has both left and right node
        Assert.assertEquals(
                "",
                65,
                tree.getValue()
        );
    }
    
    /**
     * In this case, successor is
     * third level of tree. Our
     * new tree look like that;
     *<pre> 
     *        55 
     *      /    \
     *     20     65
     *           /  \
     *          60  134 
     * </pre>
     * After delete root node (55)
     * our tree will be look like that;
     *<pre> 
     *        60 
     *      /    \
     *     20     65
     *              \
     *              134 
     * </pre>
     * If successor is 3rd level of tree,
     * we have to take two action;
     * 1- set old successor location to null
     * 2- replace successor with root node (
     *    when replacing old root node's left
     *    and right child also has to be assigned
     *    to new root node [successor])
     */
    @Test
    public void deleteRootNodeBothChild3rdLevelSuccessor(){
        Tree tree2 = new Tree(55);
        NodeM.add(tree2, new Node(20));
        NodeM.add(tree2, new Node(65));
        NodeM.add(tree2, new Node(60));
        NodeM.add(tree2, new Node(134));

        // Create a Weak Ref on obj
        WeakReference<Node> weakRef 
                  = new WeakReference<>(tree2.getRoot());
        
        NodeM.delete(tree2, null);
        
        // Keep your fingers crossed    
        System.gc();    

        // should be null if GC collected
        Assert.assertNull(
                "Deleted old root's node has to be deleted on memory",
                weakRef.get()
        );
        
        // Delete root node which has both left and right node
        Assert.assertEquals(
                "",
                60,
                tree2.getValue()
        );
    }
}
