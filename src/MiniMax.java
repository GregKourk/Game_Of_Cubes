package GameOfCubes;

import static GameOfCubes.Main.numberChosenByUser;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * @author Γρηγόρης
 */
public class MiniMax {
    
    private Tree tree;
    
    public MiniMax() {
    }
    
    public Tree getTree() {
        return tree;
    }

    //Initial tree creation with one node, which is tree's root and has as many cubes an users defines
    public void constructTree(int noOfBones) {
        tree = new Tree();
        Node root = new Node(noOfBones, true);
        
        System.out.println("Root node of initial tree to be created has number of stones: " + root.getNoOfStones() + " and is max player: " + root.isIsMaxPlayer());
        
        tree.setRoot(root);
        constructTree(root);
    }
 
    //Rest of the creation
    private void constructTree(Node parentNode) {
        List<Integer> listofPossibleHeaps 
          = GameOfCubes.getPossibleStates(parentNode.getNoOfStones());
        boolean isChildMaxPlayer = !parentNode.isIsMaxPlayer();
        listofPossibleHeaps.forEach(numberOfStones -> {
            Node newNode = new Node(numberOfStones, isChildMaxPlayer);
            
            System.out.println("Node created with number of stones: " + newNode.getNoOfStones() + " and is max player: " + newNode.isIsMaxPlayer());
            
            parentNode.addChildren(newNode);
            if (newNode.getNoOfStones() > numberChosenByUser) {
                constructTree(newNode);
            }
        });
    }
    
    //Checks if Max players is winner. If Max wins root.getScore=1, and function returns true,
    //else root.getScore=-1 and functions returns false
    public boolean checkWin() {
    Node root = tree.getRoot();
    System.out.println("Root node has number of stones: " + root.getNoOfStones() + " and is max player: " + root.isIsMaxPlayer());
    checkWin(root);
    return root.getScore() == 1;
    }
 
    //We insert score in every node if the tree. If Max can win from node it gets possitive score, else it gets negative score.
    //After this we find "best" child of every node, that is not terminal node
    private void checkWin(Node node) {
        List<Node> children = node.getChildrens();      
        boolean isMaxPlayer = node.isIsMaxPlayer();        
        children.forEach(child -> {
            System.out.println("\nChild's remaining stones: " + child.getNoOfStones() + "\nIs max: " + child.isIsMaxPlayer() + "\nNumber given by user: " + numberChosenByUser);
            
            if (child.getNoOfStones() < (numberChosenByUser+1)) {
                if (child.getNoOfStones()==2 || child.getNoOfStones()==1){
                    child.setScore(isMaxPlayer ? -1 : 1);
                    System.out.println("New score is: " + child.getScore());
                }else{
                   if (child.getNoOfStones()%3==0 && child.getNoOfStones()%numberChosenByUser!=0){
                       child.setScore(isMaxPlayer ? 1 : -1);
                       System.out.println("New score is: " + child.getScore());
                   }else{
                       child.setScore(isMaxPlayer ? -1 : 1);
                       System.out.println("New score is: " + child.getScore());
                   } 
                }
                
            }else {
                System.out.println("No comparison. Procced to the next node");
                checkWin(child);
            }
        });
        
        Node bestChild = findBestChild(isMaxPlayer, children);
        node.setScore(bestChild.getScore());
        }
    
    //Function that finds "best" kid of every node
    private Node findBestChild(boolean isMaxPlayer, List<Node> children) {
        Comparator<Node> byScoreComparator = Comparator.comparing(Node::getScore);
        return children.stream().max(isMaxPlayer ? byScoreComparator : byScoreComparator.reversed()).orElseThrow(NoSuchElementException::new);
    }
}
