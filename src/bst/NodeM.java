package bst;

import static bst.NodeH.First;
import static bst.NodeH.get;
import static bst.NodeH.parent;

/**
 * This class contains some method 
 * which is helps to mutate (add, delete, 
 * replace etc.) Binary Search Tree.
 * 
 * @author mustafakirimli
 */
public class NodeM {
    
    /**
     * Works like {@link #add}, but 
     * this function works with Tree
     * instead of Node.
     * 
     * @param tree
     * @param el
     * @return 
     */
    public static boolean add(Tree tree, Node el){
        return add(tree.getRoot(), el);
    }
    
    /**
     * Add a new node to 
     * given parent node.
     * 
     * According to the Binary Search Tree;
     * - if the new node's value is smaller 
     *   than parent's value, the new node 
     *   has to be inserted left side 
     *   (as a left node).
     * 
     * - if the new node's value is bigger 
     *   than parent's, value, the new node 
     *   has to be inserted right side 
     *   (as a right node).
     *
     * @link http://bit.ly/1V5RqKL Binary Search Tree
     * 
     * @param node parent node
     * @param el (child) node which is 
     *             to be added to parent
     * @return 
     */
    public static boolean add(Node node, Node el){
        // if new node's value bigger than 
        // parent node's value, add to right side
        if(el.getValue() > node.getValue()){
            if(node.getRight() == null){
                // if right side is empty, insert 
                // (set) new node to the right side
                node.setRight(el);
            }else{
                // if right side not empty, 
                // try to add as a child node of right
                add(node.getRight(), el);
            }
        }else if(el.getValue() < node.getValue()){
            // vice versa, add to left side 
            // (it means new node's value < parent's)
            if(node.getLeft() == null){
                // if left side is empty,  
                // insert (set) new node to the left side
                node.setLeft(el);
            }else{
                add(node.getLeft(), el);
            }
        }else{
            /*
            Tree has already have 
            the node. It means same node 
            trying to adding again. 
            We're doing nothing.
            */
        }
        return true;
    }
    
    /**
     * This method helps to insert node
     * to left or right side according to
     * the old node's value. Also (if have) 
     * pass to old node's child to new node.
     * 
     * Old node's value will be compared to
     * parent's value of determine which side
     * old node is it.
     * 
     * If node's value is equals to parent 
     * node's value, any action can't be done.
     * 
     * @param parent parent node 
     * @param node node to be replaced
     * @param el new node to be inserted
     * @return 
     */
    private static boolean replace(
            Node parent, 
            Node node, 
            Node el){    
        
        // check where the old value is 
        //it (left side or right side)
        if(node.getValue() > parent.getValue()){
            // if value is right side, 
            // delete parent node's right leaf
            parent.setRight(el);
        }else if (node.getValue() < parent.getValue()){
            // if value is left side, 
            // delete parent node's left leaf
            parent.setLeft(el);
        }else{
            // we're trying to replace a element
            // with parent's value. It can't be done.
            return false;
        }
        
        return true;
    }
    
    /**
     * Copy left and right child from 
     * "from" node to "to" node.
     * 
     * @param from
     * @param to 
     */
    public static void copyChild(Node from, Node to){
        to.setRight(from.getRight());
        to.setLeft(from.getLeft());
    }
    
    /**
     * Deletes and returns first node in 
     * given node.
     * 
     * @param node
     * @param parent
     * @return returns first node
     */
    private static Node popFirst(Node node, Node parent){
        if(node.getLeft() != null){
            return popFirst(node.getLeft(), node);
        }
        
        /*
        delete successor from current location.
        We're replacing successor with right child
        (if not have of course with null). Because
        successor node can't have left child but 
        right child can.
        */
        replace(parent, node, node.getRight());
        
        return node;
    }
    
    /**
     * Works like {@link #delete}, but 
     * this function works with integer
     * parameter.
     * 
     * @param tree
     * @param value
     * @return 
     */
    public static boolean delete(Tree tree, int value){
        Node el = get(tree, value);
        return NodeM.delete(tree, el);
    }
    
    /**
     * Delete a node on given tree.
     * 
     * @param tree the tree (root node)
     * @param el node for deletion
     * @return true in all case (?)
     */
    public static boolean delete(Tree tree, Node el){
        
        /**
         * If we're trying to delete root 
         * node, return false. Because 
         * for deleting root node deleteRoot
         * method should use.
         */
        if(null == el || el.equals(tree.getRoot())){
            return delete(tree);
        }
        
        Node parent = parent(tree.getRoot(), el);
        
        switch(NodeH.nodeType(el)){
            // CASE 1: if node is leaf
            case LEAF_NODE:
                /* In below example we're trying
                *  to delete node "2". We have to
                *  to one thing that set node "3"s
                *  left node to null.
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
                replace(parent, el, null);
                break;
            // CASE 2: has only one child
            case HAS_ONLY_ONE_CHILD:
               /* In below example we're trying
                * to delete node "3". We have to
                * to one thing that set node "20"s
                * right node to node "2".
                * <br/>
                * We can decribe this operation with 
                * psuecode like that:
                * parent.el = el.child
                * el.child = null
                * <br/>
                * For this example;
                *  - parent is: 20
                *  - el is: 3
                *  - child is: 2
                * <br/>
                * BEFORE deleting node "3":
                * <pre> 
                *        55 
                *      /    \
                *     20     65
                *    /  \      \
                *   3   45     134 
                *  /
                * 2
                * </pre>
                * <br/>
                * AFTER deleting node "3":
                * <pre> 
                *        55 
                *      /    \
                *     20     65
                *    /  \      \
                *   2   45     134 
                *
                *
                * </pre>
                */
                Node child =
                        el.getRight() != null
                        ? el.getRight() 
                        : el.getLeft();
                // test with: el have to be a leaf
                replace(el, child, null); 
                replace(parent, el, child);
                break;
            // CASE 3: has both left and right child
            case NORMAL_NODE:
                /*
                 now we have to found the 
                 minimum element (successor)
                 on the right sub tree.
                */
                Node successor = popFirst(el.getRight(), el);

                /* BEFORE deleting node "20":
                * <pre> 
                *        55 
                *      /    \
                *     20     65
                *    /  \      \
                *   3   45     134 
                *  /   /  \
                * 2   40   48
                * </pre>
                * <br/>
                * AFTER deleting node "20":
                * <pre> 
                *        55 
                *      /    \
                *     40     65
                *    /  \      \
                *   3   45     134 
                *  /      \
                * 2       48
                * </pre>
                * // delete successor's current connection
                * successor.parent.left = null (popFirst did)
                * // replace el with successor
                * el.parent.left|right = successor
                * // apply el's left and right to successor
                * successor.right = el.right 
                * successor.left = el.left 
                */
                
                // replace el with successor
                // and pass on el's child to successor.
                replace(parent, el, successor);
                copyChild(el, successor);
                break;
        }
        return true;
    }
    
    /**
     * Deletes root node.
     * 
     * @param tree
     * @return
     */
    public static boolean delete(Tree tree){        
        switch(NodeH.nodeType(tree.getRoot())){
            // CASE 1: if node is leaf
            case LEAF_NODE: 
                /*
                 set root node to null. After this 
                 operation if there is not reference 
                 to the old node, GC will delete old node.
                */
                tree.setRoot(null);
                break;
            // CASE 2: has only one child
            case HAS_ONLY_ONE_CHILD:
                /*
                in this case actually we're moving exists
                child (left of right, we've checked above)
                to the root node.
                Old node will be deleted by GC, of course 
                if there is no reference.
               */
               tree.setRoot(
                       tree.getRight() != null 
                       ? tree.getRight() 
                       : tree.getLeft()
               );
                break;
            // CASE 3: has both left and right child
            case NORMAL_NODE:
                /*
                 now we have to found the  minimum 
                element (successor) in the right sub tree
                */
                Node successor = popFirst(tree.getRight(), tree.getRoot());

                /*
                 set old root's left node 
                 as successor's left 
                */
                copyChild(tree.getRoot(), successor);

                // return new tree
                tree.setRoot(successor);
                break;
        }
        return true;
    }
}
