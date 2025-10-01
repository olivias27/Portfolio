package cs1501_p1;

public class BST<T extends Comparable<T>> implements BST_Inter<T>
{
    public BTNode<T> root;
    //Inserting a new key into the bst
    public void put(T key) 
    {
        // Keeps original root or establishes a root if the tree is empty
        root = put(root,key);
    }
    public BTNode<T> put(BTNode<T> curr, T key)
    {
        // Base case. Place to insert
        if(curr == null)
        {
            return new BTNode<T>(key);
        }
        //Key is smaller than curr's key: move to the left (left val is always smaller)
        if(key.compareTo(curr.getKey())<0)
        {
            //Moves down the left side of the tree until it finds a null . Recursively update left child
            curr.setLeft(put(curr.getLeft(), key));
        }
        // Key is larger than curr's key: go right.
        else if(key.compareTo(curr.getKey()) > 0)
        {
            //Moves down the right side of the tree until it finds a null . Recursively update right child
            curr.setRight(put(curr.getRight(), key));
        }
        //Updated curr 
        return curr;
    }

    @Override
    //Starting the search at the root
    public boolean contains(T key) 
    {
        return contains(root, key);
    }
    public boolean contains(BTNode<T> curr, T key)
    {
        // Base case: key is not in the tree, return false as it was not found
        if(curr == null)
        {
            return false;
        }
        // key is smaller than curr's key. move left
        if(key.compareTo(curr.getKey()) < 0)
        {
            //recursively checks left subtree
            return contains(curr.getLeft(), key);
        }
        // key is greater than curr's key. move right
        else if(key.compareTo(curr.getKey()) > 0)
        {
            //recursively checks right subtree
            return contains(curr.getRight(), key);
        }
        //Only other case: key matches curr's key, return true.
        return true;
    }

    @Override
    public void delete(T key) 
    {
        //If the tree is empty, sets the root as null. Otherwise keeps original root.
        root = delete(root, key);
    }
    public BTNode<T> delete(BTNode<T> curr, T key)
    {
        // Base case: key was not found in the tree and maintains tree structure
        if(curr == null)
        {
            return null;
        }
        // key is smaller than curr's key: go left
        if(key.compareTo(curr.getKey())<0)
        {
        // Recursively check and update the left subtree
          curr.setLeft(delete(curr.getLeft(),key));
        }
        // key is greater than curr's key: go right
        else if (key.compareTo(curr.getKey())>0)
        {
            // Recursively check and update the right subtree
          curr.setRight(delete(curr.getRight(),key));
        }
        //Finding the key you want to delete
        else 
        {
            if(curr.getLeft() == null)
            {
                //Case 1: Deleted node does not have a left child, return right subtree
                return curr.getRight();
            }
            else if (curr.getRight() == null)
            {
                //Case 2: Deleted node does not have a right child, return left subtree
                return curr.getLeft();
            }
            else 
            {
                //Case 3: Nde to be deleted has two children. 
                BTNode<T> n = curr.getRight();
                // Find the leftmost node in the right subtree 
                    while(n.getLeft() != null)
                    {
                        n = n.getLeft();
                    }
                //Create a new node with the smallest node's key
                BTNode<T> f = new BTNode<>(n.getKey());
                // Attaches the new node to the deleted nodes left subtree 
                f.setLeft(curr.getLeft());
                //Delete the smallest node from the right subtree
                f.setRight(delete(curr.getRight(), n.getKey()));
                //Return updated subtree
                return f;
            }
        }
       return curr; 
    }

    @Override
    public int height() 
    {
        return height(root);
    }
    public int height(BTNode<T> curr)
    {
        // Base case: If the tree is empty, return a height of 0.
        if(curr==null)
        {
            return 0;
        }
        else
        {
            // recursively calculate heights of left and right subtrees
            int l = height(curr.getLeft());
            int r = height(curr.getRight());
            //if the left subtree is longer than the right, we use the longest path to calculate our height. 
            if(l > r)
            {
                // need to add the 1 to account for the curr + tallest subtree
                return 1 + l;
            }
            else 
            {
                return 1 + r;
            }
        }
    }
    @Override
    public boolean isBalanced()
    {
        //Start at the root
        return isBalanced(root);
    }
    public boolean isBalanced(BTNode<T> curr) 
    {
        //Base Case: the tree is empty
        if(curr == null)
        {
            // an empty tree is a balanced tree
            return true;
        }
        else
        {     
            //calculate heights of left and right trees
            int l = height(curr.getLeft());
            int r = height(curr.getRight());
            // If the abs differences in heights between left and right subtrees is greater than 1, they are not balanced
            if(Math.abs(l-r)> 1)
            {
                return false;
            }
            // Recursively checks the right subtree before it starts on the left to see if they are balanced
            return isBalanced(curr.getRight()) && isBalanced(curr.getLeft());
        }
    }

    public String inOrderTraversal()
    {
        StringBuilder str = new StringBuilder();
        //Start at the root and pass in the stringbuilder to start our string
        inOrderTraversal(root, str); 
        if(str.length() > 0)
        {
            // removes the last colon in the string
            str.setLength(str.length()-1);
        }
        return str.toString();
    }
    public void inOrderTraversal(BTNode<T> curr, StringBuilder str) 
    {
        // Base case: if the root is empty or we have reached the end, terminate.
        if(curr == null)
        {
            return;
        }
        //Traverse the left tree recursively first, add the current nodes key to the string, and then traverse right subtree 
        inOrderTraversal(curr.getLeft(), str);
        str.append(curr.getKey()).append(":");
        inOrderTraversal(curr.getRight(), str);
    }
    public String serialize()
    {
        StringBuilder s = new StringBuilder();
        //parameter isRoot is only true for the first serialize call because we start with the root.
        serialize(root, s, true);
        if(s.length() > 0)
        {
            // removes the last comma
            s.setLength(s.length()-1);
        }
        return s.toString();
    }
    public void serialize(BTNode<T> curr, StringBuilder s, Boolean isRoot) 
    {
        //Base Case: we have reached a null node 
        if(curr == null)
        {
            s.append("X(NULL),"); 
            return;
        }
        //Only for the first call
        if(isRoot == true)
        {
            s.append("R(").append(curr.getKey()).append("),");
        }
        else
        {
            // Node is an interior node if it has either a right or left child
            boolean isInterior = (curr.getRight() != null || curr.getLeft()!= null);
           if(isInterior == true)
            {
                s.append("I(").append(curr.getKey()).append("),");
            }
            else
            {
                //If a node is not a root or interior node, it must be a leaf (if not null as well)
                s.append("L(").append(curr.getKey()).append("),");
                return;
            }
        }
        //Recursively serialize the left subtree first, then the right subtree (pre-order traversal)
        serialize(curr.getLeft(), s, false);
        serialize(curr.getRight(), s, false);
    }

    public BST_Inter<T> reverse() 
    {
        BST<T> rev = new BST<>();
        // starts the reverse at the root
        rev.root = reverse(this.root);
        return rev;
    }

    public BTNode<T> reverse(BTNode<T> curr)
    {
        //Base case: if we've reacehd a null node, return null (cannot traverse further)
        if(curr == null)
        {
            return null;
        }
        //Create a new node with curr's key and recursively swap it's left and right children
        BTNode<T> nn = new BTNode<>(curr.getKey());
        nn.setLeft(reverse(curr.getRight()));
        nn.setRight(reverse(curr.getLeft()));
        //Return new subtree root
        return nn;
    }
    
}