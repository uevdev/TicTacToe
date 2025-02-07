import java.util.Scanner;

public class Game {
    private Map map;
    private static int moveCount = 0;
    private final Player player = new Player();
    private final Opponent opponent = new Opponent();
    private final Scanner sc = new Scanner(System.in);


    // Старт игры
    public void start() {
        System.out.println("|| КРЕСТИКИ-НОЛИКИ ||");

        setMapSize();

        map.display();

        while (true) {
            makeNextMove();
            if (map.checkWinner()) {
                end();
                break;
            } else if (map.isFull()) {
                draw();
                break;
            }
            moveCount++;
        }
    }


    // Задать размер карты
    private void setMapSize() {
        while (true) {
            System.out.print("Введите размер матрицы игрового поля (3-10): ");
            String input = sc.nextLine(); // Читаем строку

            try {
                int size = Integer.parseInt(input); // Пробуем преобразовать строку в byte

                if (size >= 3 && size <= 10) {
                    map = new Map(size);
                    break; // Успешно установили размер, выходим из цикла
                } else {
                    System.out.println("Ошибка: размер должен быть в диапазоне от 3 до 10. Попробуйте снова.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите корректное целое число.");
            }
        }
    }


    // Выбрать ход
    public void makeNextMove() {
        if (moveCount % 2 == 0) {
            player.makeMove(map);
        } else {
            opponent.makeMove(map);
        }
    }


    // Закончить игру
    public void end() {
        if (moveCount % 2 == 0) {
            System.out.println("Поздравляем, вы выиграли!");
        } else {
            System.out.println("Вы проиграли.");
        }
        isNewGame();
    }


    // Закончить игру ничьей
    public void draw() {
        System.out.println("Ничья!");
        isNewGame();
    }


    // Продолжить играть или завершить исполнение программы
    public void isNewGame() {
        String answer = "";
        while (!(answer.equalsIgnoreCase("да") || answer.equalsIgnoreCase("нет"))) {
            System.out.print("Начать новую игру? (да/нет): ");
            answer = sc.nextLine();
        }
        if (answer.equalsIgnoreCase("да")) start();
    }
}
