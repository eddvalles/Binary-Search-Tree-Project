// A driver program that makes use of all the methods in the Binary Search Tree java class. The driver allows you
// to interact with the Binary Search Tree in multiple manners, such as inserting, finding elements, and even creating
// a default tree for quick testing.


import java.util.Scanner;
import java.util.InputMismatchException;

public class BinaryDriver {
    public static void outputOptions(){
        System.out.println("Here are your options: ");
        System.out.printf("%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n%s%n",
                "  - Default Tree",
                "  - Insert",
                "  - Find",
                "  - FindMin",
                "  - FindMax",
                "  - FindKth",
                "  - Remove",
                "  - RemoveMin",
                "  - Clear",
                "  - Print",
                "  - Quit");
    }

    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        BinarySearchTree<Integer> myTree = new BinarySearchTree<>();

        System.out.println("Welcome to Binary Tree Engine !");

        outputOptions();

        boolean quit_program = false;
        while (!(quit_program)){
            System.out.print("Please type in an option>  ");
            String userInput = input.nextLine();

            switch (userInput){
                case "Insert":
                    try{
                        System.out.print("What number would you like to insert> ");
                        Integer userNumber = input.nextInt();

                        // Insert the number into the Binary Tree
                        myTree.insert(userNumber);
                    }
                    catch (DuplicateItemException e){
                        System.out.println("Number is already in BinaryTree. No duplicates allowed.");
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid entry. You must enter an Integer.");
                    }
                    // If there is input in the buffer, discard it
                    if (input.hasNextLine()){
                        input.nextLine();
                    }
                    break;
                case "Default Tree":
                    // Default tree to work with for quick testing purposes
                    if (myTree.isEmpty()){
                        myTree.insert(8);
                        myTree.insert(6);
                        myTree.insert(2);
                        myTree.insert(7);
                        myTree.insert(10);
                        myTree.insert(12);

                        System.out.println("6 elements have been inserted into the tree. Enter 'Print' to view.");
                    }
                    else
                        System.out.println("Cannot create a default tree with populated tree. Please 'Clear' first.");
                    break;
                case "Find": // Attempts to locate a value specified by the user
                    try{
                        System.out.print("What number would you like to search for> ");
                        Integer searchNumber = input.nextInt();

                        Integer returnValue = myTree.find(searchNumber);
                        System.out.printf("Number %d %s found in the binary search tree.%n",
                                searchNumber,
                                (returnValue == null) ? "was not": "was");
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid entry. You must enter an Integer.");
                    }
                    // If there is input in the buffer, discard it
                    if (input.hasNextLine()){
                        input.nextLine();
                    }
                    break;
                case "FindMax": // Locates the maximum value in the binary search tree
                    try {
                        Integer foundMaxValue = myTree.findMax();
                        System.out.println("Maximum value found is " + foundMaxValue);
                    }
                    catch (NullPointerException e){
                        System.out.println("No maximum value found.");
                    }
                    break;
                case "FindMin": // Locates the minimum value in the binary search tree
                    try{
                        Integer foundMinValue = myTree.findMin();
                        System.out.println("Minimum value found is " + foundMinValue);
                    }
                    catch (NullPointerException e){
                        System.out.println("No minimum value found.");
                    }
                    break;
                case "FindKth": // Locates the kth smallest element located in the binary search tree
                    try {
                        System.out.print("What Kth smallest element would you like to search for> ");
                        int k = input.nextInt();

                        Integer kthSmallest = myTree.findKth(k);

                        System.out.printf("The %d%s smallest element is %d%n", k,
                                k == 1 ? "st": k == 2 ? "nd": k == 3 ? "rd" : "th",
                                kthSmallest);
                    }
                    catch (NullPointerException e){
                        System.out.println("Out of range.");
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid entry. You must enter an Integer.");
                    }
                    // If there is input in the buffer, discard it
                    if (input.hasNextLine()){
                        input.nextLine();
                    }
                    break;
                case "Remove": // Removes a specified value from the binary search tree
                    try{
                        System.out.print("What number would you like to remove> ");
                        int removeNum = input.nextInt();

                        myTree.remove(removeNum);

                        System.out.println("Number has been removed.");
                    }
                    catch (ItemNotFoundException e){
                        System.out.println("Item to remove not found.");
                    }
                    catch (InputMismatchException e){
                        System.out.println("Invalid entry. You must enter an Integer.");
                    }
                    // If there is input in the buffer, discard it
                    if (input.hasNextLine()){
                        input.nextLine();
                    }
                    break;
                case "RemoveMin": // Removes the minimum item from the binary search tree
                    try{
                        myTree.removeMin();
                        System.out.println("Minimum item has been removed.");
                    }
                    catch (ItemNotFoundException e){
                        System.out.println("Minimum item to remove not found.");
                    }
                    break;
                case "Print":
                    myTree.preOrderPrintTree();
                    break;
                case "Clear": // Will clear the binary search tree
                    if (myTree.isEmpty())
                        System.out.println("Tree is already empty.");
                    else {
                        myTree.makeEmpty();
                        System.out.println("Tree has been cleared.");
                    }
                    break;
                case "Quit" :
                    System.out.println("Quitting program.");
                    quit_program = true;
                    break;
                default:
                    System.out.println("Please enter a valid option.");
                    break;
            }
        }
    }
}
