import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Opponent {
    private static final String symbol = "⭕";
    private final Random randGen = new Random();

    private Coordinates selectMove(Map map) {
        // перебирает все ходы (в пустых точках) и выбирает ПРОСТО РАНДОМНЫЙ
        // (далее можно реализовать просчет наперед)
        List<Coordinates> moves = new ArrayList<>();

        for (int x = 0; x < map.getSize(); x++) {
            for (int y = 0; y < map.getSize(); y++) {
                Coordinates coordinates = new Coordinates(x,y);
                if (map.getCell(coordinates).isEmpty()) {
                    moves.add( coordinates );
                }
            }
        }

        return moves.get( randGen.nextInt(0, moves.size()) );
    }


    public void makeMove(Map map) {
        Coordinates move = selectMove(map);
        System.out.println("Компьютер делает ход...");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        map.updateValue(move, symbol);
    }

}
