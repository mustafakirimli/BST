package bst;

import java.util.ArrayList;
import java.util.List;


/**
 * This class contains some helper 
 * methods for Binary Search Tree.
 * 
 * @link http://bit.ly/1V5Rl9P Binary Tree
 * @link http://bit.ly/1V5RqKL Binary Search Tree
 * 
 * @author mustafakirimli
 */
public class NodeH {

    /**
     * Works like {@link #_first}, but 
     * this function returns element's
     * value not element.
     * 
     * @param node node for search
     * @return lowest (first) element
     */
    public static int first(Node node){
        return First(node).getValue();
    }
    
    /**
     * Works like {@link #_first}, but 
     * this function returns element's
     * value not element and works with
     * Tree instead of Node.
     * 
     * @param tree tree for search
     * @return lowest (first) element
     */
    public static int first(Tree tree){
        return First(tree.getRoot()).getValue();
    }
    
    /**
     * This function find the first (lowest)
     * element's value in the given tree.
     * <br/><br/>
     * While doing that, we should continue 
     * only (and till the leftmost) 
     * because all the value have to be smaller
     * than parent's.
     * <br/><br/>
     * It means we don't need to touch (in any 
     * level) right node.
     * 
     * Let's look below example: 
     *<pre> 
     *        55 
     *      /    \
     *     20     65
     *    /  \      \
     *   3   45     134 
     *  /
     * 2
     * </pre>
     * 
     * According to the example, if we're 
     * searching "<b>2</b>",  and our tree is 
     * Binary Search Tree the smallest value 
     * is always at the bottom left.
     * 
     * @param node node for search
     * @return lowest (first) element's value
     */
    public static Node First(Node node){
        // continue to the bottom left.
        if(node.getLeft() != null){
            // if left node not empty, 
            // continue to the left child
            return First(node.getLeft());
        }
        
        // return element
        return node;
    } 

    /**
     * Works like {@link #_last}, but 
     * this function returns element's
     * value not element and works with
     * Tree instead of Node.
     * 
     * @param node tree for search
     * @return highest (last) element
     */
    public static int last(Node node){
        return Last(node).getValue();
    }
    
    /**
     * Works like {@link #_last}, but 
     * this function returns element's
     * value not element.
     * 
     * @param tree node for search
     * @return highest (last) element
     */
    public static int last(Tree tree){
        return Last(tree.getRoot()).getValue();
    }
    
    /**
     * This function find the last (highest)
     * element in the given tree.
     * <br/><br/>
     * While doing that, we should continue 
     * only (and till the rightmost) 
     * because all the value have to be bigger
     * than parent's.
     * <br/><br/>
     * It means we don't need to touch (in any 
     * level) left node.
     * 
     * Let's look below example: 
     *<pre> 
     *        55 
     *      /    \
     *     20     65
     *    /  \      \
     *   3   45     134 
     *  /
     * 2
     * </pre>
     * 
     * According to the example, if we're 
     * searching "<b>134</b>",  and our tree is 
     * Binary Search Tree the biggest value 
     * is always at the bottom right.
     * 
     * @param node node for search
     * @return highest (last) element
     */
    public static Node Last(Node node){
        // continue to the bottom left.
        if(node.getRight()!= null){
            // if left node not empty, 
            // continue to the left child
            return Last(node.getRight());
        }
        
        // return element
        return node;
    }

    /**
      Works like {@link #contains}, but 
     * this function works with
     * Tree instead of Node.
     * @param tree
     * @param e
     * @return 
     */
    public static boolean contains(Tree tree, Node e){
        return contains(tree.getRoot(), e);
    }
    
    /**
     * Checks the given node exists 
     * or not. Uses {@link #subSet}
     * 
     * @param node node for search
     * @param e searched node
     * @return true if "node" is contains element "e"
     */
    public static boolean contains(Node node, Node e){
        // check element with subSet method.
        // we're passing "1" for max parameter
        // for stop searching process after our
        // node found
        List<Node> nodes = NodeH.subSet(
                node,
                e.getValue(),
                e.getValue() + 1,
                1
        );

        return nodes.size() > 0;
    }
    
    /**
     * Returns list of node (number of "max" 
     * parameter's value), which node values 
     * are between from and to.
     * 
     * @param node node for search
     * @param from searched min.
     *             integer value (inclusive)
     * @param to   searched max. 
     *             integer value (exclusive)
     * @return     list of Node if found,
     *             else returns empty list
     */
    public static List<Node> subSet(
            Node node, 
            int from, 
            int to){
        // nodes for returned result
        List<Node> nodes 
                = new ArrayList<>();
        
        // call private subSet method
        subSet(node, from, to, -1, nodes);
        
        // return all the matched nodes 
        // or empty List<Nooe>
        return nodes;
    }
    
    /**
     * Works like {@link #subSet}, but 
     * this function gives extra parameter
     * for maximum return element.
     * 
     * @param node
     * @param from
     * @param to
     * @param max  maximum node to be returned
     * @return
     */
    public static List<Node> subSet(
            Node node, 
            int from, 
            int to, 
            int max){
        // nodes for returned result
        List<Node> nodes 
                = new ArrayList<>();
        
        // call private subSet method
        subSet(node, from, to, max, nodes);
        
        // return all the matched nodes 
        // or empty List<Nooe>
        return nodes;
    }
    
    private static void subSet(
            Node node, 
            int from, 
            int to, 
            int max, 
            List<Node> nodes){
        if(isInRange(node, from - 1, to)){
            // if the node is expected range, 
            // add to the list
            nodes.add(node);
        }
        
        /*
        IF we don't reached max value,
        
        AND left node is not empty,
        
        AND searched min (from) value is smaller than 
        node's value than continue search  on left 
        side (node). 
        
        Otherwise, we don't need to search on 
        left side because according to the Binary 
        Search Tree any left node can't have bigger
        value than parent node. All left node's value
        have to smaller than parent's value.
        */ 
        if(max != nodes.size() 
                && node.getLeft() != null 
                && from < node.getValue()){
            // call subSet again (recursive call)
            subSet(
                    node.getLeft(), 
                    from, 
                    to, 
                    max, 
                    nodes
            );
        }
        
        /*
        IF we don't reached max value,
        
        AND right node is not empty,
        
        AND searched max (to) value is bigger than 
        node's value than continue search  on right
        side (node). 
        
        Otherwise, we don't need to search on 
        right side because according to the Binary 
        Search Tree any right node can't have smaller
        value than parent node. All right node's value
        have to bigger than parent's value.
        */ 
        if(max != nodes.size() 
                && node.getRight() != null 
                && to > node.getValue()){
            // call subSet again (recursive call)
            subSet(
                    node.getRight(), 
                    from, 
                    to, 
                    max, 
                    nodes
            );
        }
    }
    
    /**
     * Works like {@link #isSearchTree}, but 
     * this function gives extra parameter
     * Integer.MIN_VALUE and Integer.MAX_VALUE
     * 
     * @param node
     * @return 
     */
    public static boolean isSearchTree(Node node){
        /*
        check the given node (binary tree) 
        for BTS. We're giving min and max
        default value because of that if 
        given node have int values out of
        the range Java's int value, it's 
        also can't be a BTS.
        */
        return isSearchTree(
                node,
                Integer.MIN_VALUE, 
                Integer.MAX_VALUE
        );
    }
    
    /**
     * Check the node (BT) is it BTS or not. 
     * <br/>
     * According to the Binary Search Tree,
     * root and all sub nodes also have to
     * be Binary Search Tree. Because of that
     * this function check node and child node's
     * value (recursively) is it between min and 
     * max value. 
     * <br/><br/>
     * - It means 1: (for the first call) the parent 
     *               node's value can't be out of 
     *               Java's integer value.
     * <br/>
     * - It means 2: (for left side child nodes) the 
     *               child node's value cant be out of 
     *               range (Java's min integer values and 
     *               parent node's value) 
     * <br/>
     * - It means 3: (for right side child nodes) the 
     *               child node value cant be out of range 
     *               (and parent node's value and Java's 
     *               max integer values)
     * 
     * @param node node (Binary Tree) for check 
     * @param min min (parent's) integer value 
     *            for this node's value.
     * @param max max (parent's) integer value 
     *            for this node's value.
     * @return true if the given node and 
     *         it's sub nodes are BTS
     */
    private static boolean isSearchTree(
            Node node,
            int min,
            int max){
        if (node == null){
            // if node if null (empty), 
            // return true. We hit last element.
            return true;
        }

        // true, if the given node is 
        // a BTS else return false
        return isInRange(
                node,
                min,
                max)
                && isSearchTree(
                        node.getLeft(), 
                        min,
                        node.getValue()
                )
                && isSearchTree(
                        node.getRight(), 
                        node.getValue(), 
                        max
                );
    }
    
    /**
     * Check given node's value, 
     * is it between given range or not.
     * 
     * @param node node for checking value
     * @param min min accepted integer value
     * @param max max accepted integer value
     * @return  true if node's value between 
     *          range, false if not.
     */
    public static boolean isInRange(
            Node node, 
            int min,
            int max){
        return node.getValue() > min 
                && node.getValue() < max;
    }
    
    /**
     * Works like {@link #get}, but 
     * this function takes first parameter
     * Tree object instead of Node object.
     * 
     * @param tree
     * @param value
     * @return 
     */
    public static Node get(Tree tree, int value){
        return get(tree.getRoot(), value);
    }
    
    /**
     * Get/Find a node with given value.
     * 
     * @param node node for search
     * @param value integer for searched value
     * @return Node if found, null if not found
     */
    public static Node get(Node node, int value){
        // find node with our subSet method.
        // we're passing "1" for max parameter
        // for stop searching process after our
        // node found
        List<Node> nodes = NodeH.subSet(
                node, 
                value, 
                value + 1, 
                1
        );
        
        // if searched value found return it, 
        // else return null
        return nodes.size() > 0 ? nodes.get(0) : null;
    }
    
    /**
     * Works like {@link #parent}, but 
     * this function works with integer 
     * parameter.
     * 
     * @param node
     * @param value
     * @return 
     */
    public static Node parent(Node node, int value){
        return parent(
                node, 
                value, 
                null
        );
    }
    
    /**
     * Works like {@link #parent}, but 
     * this function works with node 
     * parameter.
     * 
     * @param node
     * @param e
     * @return 
     */
    public static Node parent(Node node, Node e){
        return parent(
                node, 
                e.getValue(), 
                null
        );
    }
    
    /**
     * Returns given node's parent node.
     * 
     * @param node node for search
     * @param value searched value
     * @param root
     * @return 
     */
    private static Node parent(
            Node node, 
            int value, 
            Node root){
        /* if we don't found child node yet, 
           continue child node search process
           on left and right side.
        */
        
        /* if left child not empty and (according 
           to the BST) node's value is smaller
           than left child search on left child and
           pass the current node as root node.
        */
        if( node.getLeft() != null 
                && value < node.getValue() ){
            return parent(
                    node.getLeft(), 
                    value, 
                    node
            );
        }
            
        /* if right child not empty and (according 
           to the BST) node's value is bigger
           than right child search on right child and
           pass the current node as root node.
        */
        if( node.getRight() != null 
                && value > node.getValue() ){
            return parent(
                    node.getRight(), 
                    value, 
                    node
            );
        }
        
        /* if we've found node, return root
           node. This root node comes from 
           previous (recursive) call.
        */
        return root;
    }
    
    public static enum NodeType{
        LEAF_NODE,            // has not any child
        HAS_ONLY_ONE_CHILD,   // has only one child
        NORMAL_NODE           // both child have
    }
    
    /**
     * This method checks and returns 
     * node type for below condition.
     * 
     * - Check the given node is it leaf 
     *   (with no children) node.
     * - Check the given node has it only
     *   one child.
     * - Check the given node is it normal
     *   node (has both left and right child)
     * 
     * @param node
     * @return 
     */
    public static NodeType nodeType(Node node){
        /*
        check given nodes children is it 
        null or not
        */
        boolean left = node.getLeft() == null;
        boolean right = node.getRight() == null;
        
        if( left && right ){
            /*
            if both children are null, this 
            node is a leaf node.
            */
            return NodeType.LEAF_NODE;
        }else if(left ^ right){
            /*
            if given node has only one child,
            this node type is HAS_ONLY_ONE_CHILD
            node.
            */ 
            return NodeType.HAS_ONLY_ONE_CHILD;
        }else{
            /* 
            given node has both left and right 
            child. this node type is NORMAL_NODE.
            */
            return NodeType.NORMAL_NODE;
        }
    }
}
