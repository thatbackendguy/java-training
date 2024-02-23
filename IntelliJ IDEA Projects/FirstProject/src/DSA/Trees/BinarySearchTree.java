package DSA.Trees;

public class BinarySearchTree
{

    static Node insertNode(Node root, int data)
    {
        if(root == null)
            return new Node(data);

        if(root.data > data)
        {
            root.left = insertNode(root.left, data);
        }
        else if(root.data < data)
        {
            root.right = insertNode(root.right, data);
        }

        return root;
    }

    public static void main(String[] args)
    {
        Node root = null;
        int arr[] = {5, 4, 2, 6, 1, 3};
        for(int j : arr)
        {
            root = insertNode(root, j);
        }

        BinaryTreeDemo.inOrder(root);
    }
}
