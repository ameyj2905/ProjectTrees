package trees;

import java.util.*;

public class BinaryTreeAlgorithms {
    /**
     * Performs a pre-order traversal of a binary tree.
     * @param root Root of binary tree.
     * @param <T> Type of node payload.
     * @return A Collection containing the node payloads in traversal order.
     */
    public static <T> List<T> preOrder(BinaryNode<T> root) {
        /* YOUR CODE HERE */
        // This is for the pre order test
        List<T>values=new ArrayList<T>();
        if(root==null)
        {
            return values;
        }
        Stack<BinaryNode<T>> stack=new Stack<BinaryNode<T>>();
        stack.push(root);
        while(!stack.isEmpty())
        {
            BinaryNode<T> current=stack.pop();
            values.add(current.payload);
            if(current.right!=null)
            {
                stack.push(current.right);
            }
            if(current.left!=null)
            {
                stack.push(current.left);
            }
        }
        return values;
    }

    /**
     * Performs a in-order traversal of a binary tree.
     * @param root Root of binary tree.
     * @param <T> Type of node payload.
     * @return A Collection containing the node payloads in traversal order.
     */
    public static <T> List<T> inOrder(BinaryNode<T> root) {
        /* YOUR CODE HERE */
        // for the inOrder test
        List<T>values=new ArrayList<T>();
        if(root==null)
        {
            return values;
        }
        Stack<BinaryNode<T>> stack=new Stack<BinaryNode<T>>();
        BinaryNode<T> current=root;

        while(!stack.isEmpty()||current!=null)
        {
            while(current!=null)
            {
                stack.push(current);
                current=current.left;
            }
            current=stack.pop();
            values.add(current.payload);
            current=current.right;

        }
        return values;


    }

    /**
     * Performs a post-order traversal of a binary tree.
     * @param root Root of binary tree.
     * @param <T> Type of node payload.
     * @return A Collection containing the node payloads in traversal order.
     */
    public static <T> List<T> postOrder(BinaryNode<T> root) {
        /* YOUR CODE HERE */
        // for the post order test
        List<T>values=new ArrayList<T>();
        if(root==null)
        {
            return values;
        }
        Stack<BinaryNode<T>> stack=new Stack<BinaryNode<T>>();
        stack.push(root);
        while(!stack.isEmpty())
        {
            BinaryNode<T> current=stack.pop();
            values.add(0,current.payload);
            if(current.left!=null)
            {
                stack.push(current.left);
            }
            if(current.right!=null)
            {
                stack.push(current.right);
            }
        }
        return values;

    }

    /**
     * Conduct a binary search on a binary search tree for a target value.
     * @param root Root of the binary search tree.
     * @param value The value to search for.
     * @return The node containing the value, or null if the value is not present in the tree.
     */
    public static BinaryNode<Integer> binarySearch(BinaryNode<Integer> root, Integer value) {
        /* YOUR CODE HERE */
        BinaryNode<Integer> node=null;
        if(root==null)
        {
            return null;
        }
        if(root.payload==value)
        {
            return root;
        }
        if(root.left!=null)
        {
            node=binarySearch(root.left,value);
        }
        if(node==null)
        {
            node=binarySearch(root.right,value);
        }
        return node;
    }

    /**
     * Inserts an Integer value into a Binary Search Tree.
     * @param root Root of the binary search tree.
     * @param value The value to insert.
     * @return The BinaryNode containing the newly inserted value, or an existing BinaryNode with an equal value.
     */
    public static BinaryNode<Integer> insert(BinaryNode<Integer> root, Integer value) {
        /* YOUR CODE HERE */
        if(root==null)
        {
            return new BinaryNode<Integer>(value);
        }
        if(root.payload==value){

            return root;
        }


        if (value<root.payload)
        {

            if(root.left!=null)
            {
                return insert(root.left,value);
            }
            else
            {
                root.left=new BinaryNode<>(value);
                return root.left;
            }
        }
        if(value>root.payload) {


            if(root.right!=null)
            {return insert(root.right,value);}
            else
            {
                root.right=new BinaryNode<>(value);
                return root.right;

            }
        }

        return new BinaryNode<>(value);


    }

    /**
     * Determines if two BSTs are equal in value.
     * @param A Root of first tree.
     * @param B Root of second tree.
     * @return True or false depending on the equality of the two trees.
     */
    public static boolean equals(BinaryNode<Integer> A, BinaryNode<Integer> B) {
        /* YOUR CODE HERE */
        if(A==null && B==null)
        {
            return true;
        }

        return (A!=null && B!=null) && (A.payload==B.payload) && equals(A.left,B.left) && equals(A.right,B.right);
    }

    /**
     * Finds the path from the tree root to a target element.
     * This algorithm does NOT assume the tree is a Binary Search Tree,
     * only that it is a Binary Tree.
     *
     * Hint: This method is a bit harder than the ones above.
     * Consider implementing some TreeAlgorithms first to get some more recursion practice.
     *
     * Hint: You can use the LinkedList::addAll method to append all the contents of
     * another list to a list (like a join, but copies and is non-destructive).
     * You may also use the LinkedList::addFirst method to push to the front of the list.
     *
     * @param root Root of the tree.
     * @param value The value to search for.
     * @param <T> The type of the value to search for.
     * @return A LinkedList of Directions that lead to the target value.
     * If the target value is at the root element, return an empty list.
     * If the target value is not present in the tree, return null.
     */
    public static <T> LinkedList<BinaryNode.Direction> path(BinaryNode<T> root, T value) {
        /* YOUR CODE HERE */
        LinkedList<BinaryNode.Direction> direction=new LinkedList<>();
        if(root==null)
        {
            return direction;
        }
        if(root.payload==value) {
            return direction;
        }
        if(!root.payload.equals(value))
        {
            direction.add(BinaryNode.Direction.left);
        }
        return null;
    }
}
