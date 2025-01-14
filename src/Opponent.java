import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class Opponent {
    private final String symbol;
    private final Random randGen;

    Opponent() {
        this.symbol = "⭕";
        this.randGen = new Random();
    }

    private ArrayList<Integer> selectMove(Map map) {
        // перебирает все ходы (в пустых точках) и выбирает ПРОСТО РАНДОМНЫЙ (далее можно реализовать просчет наперед)
        ArrayList<ArrayList<Integer>> moveArray = new ArrayList<>();

        for (int i = 0; i < map.getSize(); i++) {
            for (int j = 0; j < map.getSize(); j++) {
                if (map.getCell(i,j).isEmpty()) {
                    moveArray.add( new ArrayList<>( Arrays.asList(i,j) ) );
                }
            }
        }

        return moveArray.get( randGen.nextInt(0, moveArray.size()) );
    }

    public void makeMove(Map map) {
        ArrayList<Integer> currMove = selectMove(map);
        System.out.println("Компьютер делает ход...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        map.updateValue(symbol, currMove.get(0), currMove.get(1));
    }

}
