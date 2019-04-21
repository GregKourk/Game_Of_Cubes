package GameOfCubes;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import static GameOfCubes.Main.numberChosenByUser;
import java.util.ArrayList;

/**
 *
 * @author Γρηγόρης
 */
public class GameOfCubes {
    private static List<Integer> possibleStatesList = new ArrayList<>(); 
    
    //Creates list of integers, which are possible states according to the input state (node)
    //and values 1,2 and user defined value
    static List<Integer> getPossibleStates(int noOfStonesInHeap) {
        possibleStatesList =  IntStream.of(1, 2, numberChosenByUser).boxed()
          .map(i -> noOfStonesInHeap - i)
          .filter(newHeapCount -> newHeapCount >= 0)
          .collect(Collectors.toList());
        return possibleStatesList;
    }    
}
