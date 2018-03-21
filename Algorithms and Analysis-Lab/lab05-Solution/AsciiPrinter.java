import java.util.*;

/**
 * Java code to print out binary tree in ASCII.
 * 
 * Code obtained from: http://stackoverflow.com/questions/4965335/how-to-print-binary-tree-diagram
 * Author: michal.kreuzman on Stackoverflow
 * Modified: Jeffrey Chan, 2016
 */
public class AsciiPrinter 
{

	/**
	 * Print out tree rooted at 'root'.
	 */
    public static void printNode(Node root) {
        int maxLevel = AsciiPrinter.maxLevel(root);

        printNodeInternal(Collections.singletonList(root), 1, maxLevel);
    } // end of printNode()

    /**
     * Print out internals of tree.
     */
    private static void printNodeInternal(List<Node> nodes, int level, int maxLevel) {
        if (nodes.isEmpty() || AsciiPrinter.isAllElementsNull(nodes))
            return;

        int floor = maxLevel - level;
        int endgeLines = (int) Math.pow(2, (Math.max(floor - 1, 0)));
        int firstSpaces = (int) Math.pow(2, (floor)) - 1;
        int betweenSpaces = (int) Math.pow(2, (floor + 1)) - 1;

        AsciiPrinter.printWhitespaces(firstSpaces);

        List<Node> newNodes = new ArrayList<Node>();
        for (Node node : nodes) {
            if (node != null) {
                System.out.print(node.key());
                newNodes.add(node.leftChild());
                newNodes.add(node.rightChild());
            } else {
                newNodes.add(null);
                newNodes.add(null);
                System.out.print(" ");
            }

            AsciiPrinter.printWhitespaces(betweenSpaces);
        }
        System.out.println("");

        for (int i = 1; i <= endgeLines; i++) {
            for (int j = 0; j < nodes.size(); j++) {
                AsciiPrinter.printWhitespaces(firstSpaces - i);
                if (nodes.get(j) == null) {
                    AsciiPrinter.printWhitespaces(endgeLines + endgeLines + i + 1);
                    continue;
                }

                if (nodes.get(j).leftChild() != null)
                    System.out.print("/");
                else
                    AsciiPrinter.printWhitespaces(1);

                AsciiPrinter.printWhitespaces(i + i - 1);

                if (nodes.get(j).rightChild() != null)
                    System.out.print("\\");
                else
                    AsciiPrinter.printWhitespaces(1);

                AsciiPrinter.printWhitespaces(endgeLines + endgeLines - i);
            }

            System.out.println("");
        }

        printNodeInternal(newNodes, level + 1, maxLevel);
    } // end of printNodeInternal()

    
    /**
     * Print write spaces.
     */
    private static void printWhitespaces(int count) {
        for (int i = 0; i < count; i++)
            System.out.print(" ");
    } // end of printWhitespaces

    
    /**
     * Determine maxlevel (height) of tree.
     */
    private static int maxLevel(Node node) {
        if (node == null)
            return 0;

        return Math.max(AsciiPrinter.maxLevel(node.leftChild()), AsciiPrinter.maxLevel(node.rightChild())) + 1;
    } // end of maxLevel()

    
    /**
     * Check if all elements in list are null.
     */
    private static boolean isAllElementsNull(List list) {
        for (Object object : list) {
            if (object != null)
                return false;
        }

        return true;
    } // end of isAllElementNull()

} // end of class AsciiPrinter