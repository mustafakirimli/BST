
import bst.Node;
import bst.Tree;
import org.junit.Assert;
import org.junit.Test;


/**
 *
 * @author mustafakirimli
 */
public class TreeTest {
    @Test
    public void treeCheckRoot(){
        Node root = new Node(55);
        Tree tree = new Tree(root);
        Assert.assertEquals(
                "",
                55,
                tree.getRoot().getValue()
        );
    }
    
    @Test
    public void treeChangeRoot(){
        Node root = new Node(55);
        Node root2 = new Node(45);
        Tree tree = new Tree(root);
        tree.setRoot(root2);
        Assert.assertEquals(
                "",
                45,
                tree.getRoot().getValue()
        );
    }
}
