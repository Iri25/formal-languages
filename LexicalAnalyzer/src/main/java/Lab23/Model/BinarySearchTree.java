package Lab23.Model;

public class BinarySearchTree {
    static class Node {
        int data;
        Node left, right, next;

        Node(int key) {
            data = key;
            left = right = next = null;
        }
    }

    // function that corrects the BST using morris traversal and returns its root
    static Node fixBST(Node root) {
        Node prev, first, second;
        prev = first = second = null;

        // stores rightmost node in left subtree of root
        Node rightMost = null;
        // stores current node starting with root node 
        Node curr = root;

        while (curr != null) {
            // for each node, we compare it with prev node as we did in in-order-traversal
            if (prev != null && curr.data < prev.data) {
                if (first == null)
                    first = prev;

                second = curr;
            }

            if (curr.left != null) {
                // got left tree, then let's locate its rightmost node in left tree
                rightMost = curr.left;
                // we may have visited the left tree before, and connect the rightmost node with curr node (root node)
                while (rightMost.right != null && rightMost.right != curr)
                    rightMost = rightMost.right;

                if (rightMost.right == curr) {
                    // if this left tree has been visited before, then we are done with it
                    // cut the connection with currNode and start visit curr's right tree
                    rightMost.right = null;
                    prev = curr;
                    curr = curr.right;
                } else {
                    // if this left tree has not been visited before, then we create a back edge from rightmost node
                    // to curr node, so we can return to the start point after done the left tree
                    rightMost.right = curr;
                    curr = curr.left;
                }

            } else {
                // no left tree, then just visit its right tree
                prev = curr;
                curr = curr.right;
            }
        }

        // swap the values of incorrect nodes
        int temp = first.data;
        first.data = second.data;
        second.data = temp;

        return root;
    }

    // function to simply perform inorder traversal of the tree
    static void inorder(Node root) {
        if (root == null)
            return;

        inorder(root.left);
        System.out.print(root.data + " ");
        inorder(root.right);
    }
}