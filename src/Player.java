import java.util.Scanner;

public class Player {
    private static final String symbol = "❌";
    private final Scanner scanner = new Scanner(System.in);

    public void makeMove(Map map) {
        while (true) {
            try  {
                System.out.print("Введите ход в формате <x y>, где x,y - индексы массива Map: ");
                String[] move = scanner.nextLine().trim().split(" ");
                Coordinates coordinates = new Coordinates(Integer.parseInt(move[0]),Integer.parseInt(move[1]));
                if (map.getCell(coordinates).isEmpty()) {
                    map.updateValue(coordinates, symbol);
                    break;
                }
            } catch (Exception _) {}
        }
    }
}
