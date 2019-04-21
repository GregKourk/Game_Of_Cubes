package GameOfCubes;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Γρηγόρης
 */

public class Node {
    
    private int noOfStones;
    private boolean isMaxPlayer;
    private int score;
    List<Node> children;

    
    Node(int noOfStones, boolean b) {
        this.noOfStones = noOfStones;
        this.isMaxPlayer = b;
        children = new ArrayList<>();
    }
    
    public int getNoOfStones() {
        return noOfStones;
    }

    public boolean isIsMaxPlayer() {
        return isMaxPlayer;
    }

    public int getScore() {
        return score;
    }
    
    public void setNoOfStones(int noOfStones) {
        this.noOfStones = noOfStones;
    }

    public void setIsMaxPlayer(boolean isMaxPlayer) {
        this.isMaxPlayer = isMaxPlayer;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setChildren(List<Node> children) {
        this.children = children;
    }
    
    public void addChildren(Node n){
        children.add(n);
    }
    
    public List<Node> getChildrens() {
        return children;
    }
    
    public void removeChildren(Node n){
        children.remove(n);
    }
}

