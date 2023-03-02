public class BinaryNode<AnyType>{
    // Data; accessible by other package routines
    AnyType element;            // The data in the node
    BinaryNode<AnyType> left;   // Left child
    BinaryNode<AnyType> right;  // Right child

    // Constructor
    BinaryNode(AnyType theElement){
        element = theElement;
        left = right = null;
    }
}
