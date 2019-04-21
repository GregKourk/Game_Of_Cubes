package GameOfCubes;

import javax.swing.JOptionPane;

/**
 *
 * @author Γρηγόρης
 */
public class Main {
        public static int numberChosenByUser;
        public static int numberOfCubes ;
        
    public static void main(String argv[]) throws Exception{

        boolean result;
        String tempCubes = null;
        String tempNumber = null;
        String winner = null;
        
        Tree gameTree = new Tree();
        MiniMax miniMax = new MiniMax();

        
        tempCubes = JOptionPane.showInputDialog(null, "Insert number of cubes");
        numberOfCubes  = Integer.parseInt(tempCubes);
        while (numberOfCubes<4){
            JOptionPane.showMessageDialog(null, "Number of cubes must be at least 4!!", "Insert number of cubes", JOptionPane.INFORMATION_MESSAGE);
            tempCubes = JOptionPane.showInputDialog(null, "Insert number of cubes");
            numberOfCubes  = Integer.parseInt(tempCubes);
        }
        System.out.println("Inserted number of cubes: " + numberOfCubes);
        
        
        tempNumber = JOptionPane.showInputDialog(null, "Insert number of cubes that player can remove from table");
        numberChosenByUser = Integer.parseInt(tempNumber);
        while (numberChosenByUser<3 || numberChosenByUser>numberOfCubes){
            if (numberChosenByUser<3){
                JOptionPane.showMessageDialog(null, "Number of cubes selected must be at least 3!!", "Insert number of cubes", JOptionPane.INFORMATION_MESSAGE);
                tempNumber = JOptionPane.showInputDialog(null, "Insert number of cubes that player can remove from table");
                numberChosenByUser = Integer.parseInt(tempNumber);
            }
            if (numberChosenByUser>numberOfCubes){
                JOptionPane.showMessageDialog(null, "Number of cubes selected must be at most " + numberOfCubes + "!!", "Insert number of cubes", JOptionPane.INFORMATION_MESSAGE);
                tempNumber = JOptionPane.showInputDialog(null, "Insert number of cubes that player can remove from table");
                numberChosenByUser = Integer.parseInt(tempNumber);
            }
        }
        System.out.println("Number of cubes selected by user: " + numberChosenByUser);
        
        if((numberOfCubes - numberChosenByUser)>30){
            JOptionPane.showMessageDialog(null, "Difference between cubes inserted and cuber selected by user too large. Calculation may delay!!");
        }

        System.out.println("n*************************START OF TREE CRATION****************");
        miniMax.constructTree(numberOfCubes);
        gameTree = miniMax.getTree();
        System.out.println("*************************TREE CREATED*************************\n");
        result = miniMax.checkWin();
        if (result==true){
            winner = "Max Player wins game";
        }
        if (result==false){
            winner = "Min Player wins game";
        }
        System.out.println("\n\n" + winner + "\n\n");
        JOptionPane.showMessageDialog(null, "" + winner);      
    }
}

