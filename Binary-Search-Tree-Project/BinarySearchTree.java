// ************************MODIFIED OPERATIONS****************************************
// Comparable find(x)       --> Return item that matches x; Recursive
// Comparable findMin()     --> Return smallest item;       Recursive
// Comparable findMax()     --> Return largest item;        Recursive
// Comparable findKth()     --> Return kth smallest;        Non-Recursive
// void preOrderPrintTree() --> Prints contents of the binary search tree with '*'


public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {
    protected BinaryNode<AnyType> root;

    private static class BinaryNodeWithSize<AnyType> extends BinaryNode<AnyType> {
        BinaryNodeWithSize( AnyType x ) {
            super( x );
            size = 0;
        }
        int size;
    }

    public BinarySearchTree(){
        root = null;
    }

    public void insert(AnyType x){ root = insert(x, root); }
    public void remove(AnyType x){ root = remove(x, root); }
    public void removeMin(){ root = removeMin(root); }
    public AnyType find(AnyType x){ return elementAt(find(x, root)); }
    public AnyType findMin(){return elementAt(findMin(root));}
    public AnyType findMax(){return elementAt(findMax(root));}
    public AnyType findKth(int k){ return findKth( k, root).element;}
    public void preOrderPrintTree(){
        if (isEmpty()) // If our binary search tree is empty, notify the user
            System.out.println("No nodes in tree.");
        else // Call the print method and pass the
            preorderPrintTree(root, 0);
    }
    public boolean isEmpty(){ return root == null; }
    public void makeEmpty(){ root = null; }

    /**
     *Internal method to get element field.
     * @param t the node
     * @return the element field or null if t is null
     */
    private AnyType elementAt(BinaryNode<AnyType> t){
        return t == null ? null : t.element;
    }

    /**
     * Internal method to insert into a subtree
     * @param x the item to insert
     * @param t the node that roots the tree
     * @return the new root
     * @throws DuplicateItemException if x is already present
     */
    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> tt){
        BinaryNodeWithSize<AnyType> t = (BinaryNodeWithSize<AnyType>) tt;

        if (t == null)
            t = new BinaryNodeWithSize<AnyType>(x);
        else if (x.compareTo(t.element) < 0)
            t.left = insert(x, t.left);
        else if (x.compareTo(t.element) > 0)
            t.right = insert(x, t.right);
        else
            throw new DuplicateItemException(x.toString()); // Duplicate
        t.size++;
        return t;
    }
    /**
     * Internal method to remove from a subtree
     * @param x the item to remove
     * @param tt the node that roots the tree
     * @return the new root
     * @throws ItemNotFoundException if x is not found
     */
    protected BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> tt){
        BinaryNodeWithSize<AnyType> t = (BinaryNodeWithSize<AnyType>) tt;

        if (t == null)
            throw new ItemNotFoundException(x.toString());
        if (x.compareTo(t.element) < 0)
            t.left = remove(x, t.left);
        else if (x.compareTo(t.element) > 0)
            t.right = remove(x, t.right);
        else if (t.left != null && t.right != null){ // Two children
            t.element = findMin(t.right).element;
            t.right = removeMin(t.right);
        }
        else
            return (t.left != null) ? t.left : t.right;

        t.size--;
        return t;
    }

    /**
     * Internal method to remove minimum item from a subtree
     * @param t the node that roots the tree
     * @return the new root
     * @throws ItemNotFoundException if t is empty
     */
    protected BinaryNode<AnyType> removeMin(BinaryNode<AnyType> tt){
        BinaryNodeWithSize<AnyType> t = (BinaryNodeWithSize<AnyType>) tt;

        if (t == null)
            throw new ItemNotFoundException("Item not found");
        else if (t.left != null){
            t.left = removeMin(t.left);
            t.size--;
            return t;
        }
        else
            return t.right;
    }

    // A recursive implementation of the find method
    private BinaryNode<AnyType> find(AnyType x, BinaryNode<AnyType> t){
        // If x is less than the value at the current node, recursively call this method
        // but on that left node
        if (t != null && x.compareTo(t.element) < 0){
            return find(x, t.left); // Recursive call
        }
        // If x is greater than the value at the current node, recursively call this method
        // but on that right node
        else if (t != null && x.compareTo(t.element) > 0){
            return find(x, t.right); // Recursive call
        }
        return t; // Means we found match, so return the node
    }

    // A recursive implementation of the findMin method
    protected BinaryNode<AnyType> findMin(BinaryNode<AnyType> t){
        if (t.left == null){ // Base case; Means we have reached the leftmost minimum element
            return t;
        }
        return findMin(t.left); // Recursive call
    }

    // A recursive implementation of the findMax method
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t){
        if (t.right == null){ // Base case; Means we have reached the rightmost maximum element
            return t;
        }
        return findMax(t.right); // Recursive call
    }

    // A non-recursive implementation of the findKth method
    protected BinaryNode<AnyType> findKth(int k, BinaryNode<AnyType> t){
        while (t != null){
            int leftSize = (t.left != null) ? ((BinaryNodeWithSize<AnyType>) t.left).size : 0;

            if (k == leftSize + 1){
                return t; // Found Kth smallest
            }

            if (k <= leftSize){
                t = t.left; // Move into the left subtree
            }
            else {
                k = k - leftSize - 1;
                t = t.right; // Move into the right subtree
            }
        }
        return null; // Not found
    }

    // Print the tree contents in preOrder. It will display the contents of the binary
    // tree in the same manner that the directories are displayed in, in Figure 18.6
    // by calculating the depth of the node
    private void preorderPrintTree(BinaryNode<AnyType> t, int depth){
        if (t != null){
            printName(t.element, depth);                    // Node
            preorderPrintTree(t.left, depth + 1);     // Left
            preorderPrintTree(t.right, depth + 1);    // Right
        }
    }

    public void printName(AnyType ElementToPrint, int depth){
        for (int i = 0; i < depth; i++){
            System.out.print("****");
        }
        System.out.println(ElementToPrint);
    }
}