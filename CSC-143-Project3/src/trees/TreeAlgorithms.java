
package trees;

import java.util.*;

public class TreeAlgorithms {
    /**
     * Finds the maximum Integer in a tree.
     * @param root Root of the tree.
     * @return The maximum Integer contained in the tree; null if the root is null.
     */
    public static Integer max(TreeNode<Integer> root) {
        /* YOUR CODE HERE */
        if(root==null)
        {
            return null;
        }
        int max=root.payload;
        if(root.children!=null && !root.children.isEmpty())
        {
            for(TreeNode<Integer> ch:root.children)
            {
                max=Math.max(max,max(ch));
            }
        }
        return max;
    }

    /**
     * Finds the minimum Integer in a tree.
     * @param root Root of the tree.
     * @return The minimum Integer contained in the tree; null if the root is null.
     */
    public static Integer min(TreeNode<Integer> root) {
        /* YOUR CODE HERE */
        if(root==null)
        {
            return null;
        }
        int min=root.payload;
        if(root.children!=null && !root.children.isEmpty())
        {
            for(TreeNode<Integer> ch:root.children)
            {
                min=Math.min(min,min(ch));
            }
        }
        return min;
    }

    /**
     * Finds all the tree leaves (nodes with no children) in a tree.
     * @param root Root of the tree.
     * @return A LinkedList of leaf TreeNodes from the tree.
     */
    public static LinkedList<TreeNode<Integer>> leaves(TreeNode<Integer> root) {
        /* YOUR CODE HERE */
        LinkedList<TreeNode<Integer>> leaves=new LinkedList<>();

        if(root==null)
        {
            return leaves;
        }
        if(root.children==null|| root.children.size()==0)
        {
            return leaves;
        }
        else
        {
            return find(root,leaves);
        }

    }
    public static LinkedList<TreeNode<Integer>> find(TreeNode<Integer> root,LinkedList<TreeNode<Integer>> list )
    {
        if(root.children.size()==0)
        {
            list.add(root);
        }
        for(TreeNode<Integer> child:root.children)
        {
            find(child,list);
        }
        return list;
    }

    /**
     * Counts the number of nodes in a tree.
     * @param root Root of the tree.
     * @return
     */
    public static int count(TreeNode<Integer> root) {
        /* YOUR CODE HERE */
        if(root==null)
        {
            return 0;
        }
        int count=0;
        count++;
        if(root.children.size()!=0)
        {
            for(TreeNode<Integer> ch: root.children){
                count=count+count(ch);
            }
        }
        return count;

    }

    /**
     * Computes the depth (height) of a tree.
     * A single node by itself has zero depth;
     * a single node and a single child has a depth of 1.
     * @param root Root of the tree.
     * @return The depth (height) of the tree.
     */
    public static <T> int depth(TreeNode<T> root) {
        /* YOUR CODE HERE */
        if(root==null || root.children==null || root.children.size()==0)
        {
            return 0;
        }
        int maxdepth=0;
        for(TreeNode<T> child: root.children)
        {
            maxdepth=Math.max(maxdepth,depth(child));
        }
        return maxdepth+1;
    }

    /**
     * Determines if two trees are equal in value.
     * @param A Root of the first tree.
     * @param B Root of the second tree.
     * @param <T> Type of value contained by the tree.
     * @return True or false depending on the equality of the two trees.
     */
    public static <T> boolean equals(TreeNode<Integer> A, TreeNode<Integer> B) {
        /* YOUR CODE HERE */
        if( A==null && B==null)
        {
            return true;
        }
        if(A==null || B==null)
        {
            return false;
        }
        if(A.payload!=B.payload)
        {
            return false;
        }
        if(A.children.size()!=B.children.size())
        {
            return false;
        }


        // the number of children is greater than 0
        if(A.children.size()>0 && B.children.size()>0)
        {
            HashMap< T,Integer> map=new HashMap<>();
            for(TreeNode node:A.children)
            {
                map.put((T) node.payload,0);
            }
            for(TreeNode node1: B.children)
            {
                Integer current=map.get(node1.payload);
                if(current==null)
                {
                    return false;
                }
                map.put((T)node1.payload,++current);
            }
            for(Integer i: map.values())// to check the level
            {
                if(i!=1)
                {
                    return false;
                }
            }
            for(TreeNode node:A.children) // A nested loop to check if the Keys are matching for the different values.
            {
                for(TreeNode node1: B.children)
                {
                    if(node.payload==node1.payload)
                    {
                        return equals(node,node1);// recursive call to check the children for the respective tree nodes.
                    }
                }
            }
        }
        return true;


    }



    /**
     * Conducts a breadth first search on a tree, from top to bottom, left to right.
     *
     * Hint: use a Java Queue, rather than recursion (which depends on the Stack).
     * You can add and remove to the queue using Queue::add(e) and Queue::remove, respectively.
     *
     * @param root Root of the tree.
     * @return List of elements in the tree, in order of BFS search.
     */
    public static LinkedList<Integer> bfs(TreeNode<Integer> root) {
        /* YOUR CODE HERE */
        LinkedList<Integer> list=new LinkedList<>();
        if(root==null)
        {
            return list;
        }
        list.add(root.payload);
        if(root.children.size()==0)
        {

            return list;
        }
        Queue<TreeNode> q=new LinkedList<>();
        q.add(root);
        while(!q.isEmpty())
        {
            ArrayList<TreeNode> level=new ArrayList<>();
            int size=q.size();
            for(int i=0;i<size;i++)
            {
                TreeNode<Integer> current=q.remove();
                level.addAll(current.children);
            }
            for(TreeNode<Integer> currentNode:level)
            {
                list.add(currentNode.payload);
            }
            q.addAll(level);
        }
        return list;
    }

    /**
     * Finds the path from a tree root to a target element.
     *
     * Note: unlike BinaryTreeAlgorithms::path, this method returns a list of nodes
     * rather than a list of directions (enums). Furthermore, this method returns
     * an empty list when there is no path, while the BinaryTreeAlgorithms::path method
     * will return null if there is no path.
     *
     * @param root Root of the tree.
     * @param value Value to search for.
     * @return A LinkedList of TreeNodes, starting with the root node, describing the path of nodes
     * from the root to the node containing the target value.
     * If the target element is not present in the tree, return an empty list.
     */
    public static <T> LinkedList<TreeNode<T>> path(TreeNode<T> root, T value) {
        /* YOUR CODE HERE */
        LinkedList<TreeNode<T>> pathList=new LinkedList<>();
        if(root==null)
        {
            return pathList;
        }
        ArrayList<TreeNode<T>> list=finder(root,root,value,new ArrayList<>());
        for(int i=list.size()-1;i>=0;i--)
        {
            pathList.add(list.get(i));
        }
        return pathList;
    }
    public static <T> ArrayList<TreeNode<T>> finder(TreeNode<T> root,TreeNode<T> recent,T value,ArrayList<TreeNode<T>> list)
    {
        for(TreeNode curr:recent.children)
        {
            if(curr.payload==value)
            {
                list.add(curr);
            }
            if(list.size()<1)
            {
                list=finder(root,curr,value,list);
            }
            if(list.size()>0)
            {
                list.add(recent);
                return list;
            }
        }
        return list;
    }
}

