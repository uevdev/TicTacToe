import java.util.Scanner;

public class Player {
    private final String symbol;
    private final Scanner scanner;

    Player() {
        this.symbol = "❌";
        this.scanner = new Scanner(System.in);
    }

    public void makeMove(Map map) {
        while (true) {
            try {
                System.out.print("Введите ход в формате <x y>, где x,y - индексы массива Map: ");
                String[] currMove = scanner.nextLine().trim().split(" ");
                int currI = Integer.parseInt(currMove[0]);
                int currJ = Integer.parseInt(currMove[1]);

                if (map.getCell(currI,currJ).isEmpty()) {
                    map.updateValue(symbol, currI, currJ);

                    break;
                }
            } catch (Exception ignored) {}
        }

































    }
}
