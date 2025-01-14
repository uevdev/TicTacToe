import java.util.Scanner;

public class Game {
    private Map map;
    private static int moveCount = 0;
    private final Player player;
    private final Opponent opponent;
    private final Scanner sc;


    Game() {
        this.sc = new Scanner(System.in);
        this.player = new Player();
        this.opponent = new Opponent();
    }

    public void startGame() {
        System.out.println("|| КРЕСТИКИ-НОЛИКИ ||");

        setMapSize();

        map.display();

        while (true) {
            makeNextMove();
            if (map.checkWinner()) {
                endGame();
                break;
            } else if (map.isFull()) {
                draw();
                break;
            }
            moveCount++;
        }
    }

    private void setMapSize() {
        while (true) {
            System.out.print("Введите размер матрицы игрового поля (3-10): ");
            String input = sc.nextLine(); // Читаем строку

            try {
                int size = Byte.parseByte(input); // Пробуем преобразовать строку в byte

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



    public void makeNextMove() {
        if (moveCount % 2 == 0) {
            player.makeMove(map);
        } else {
            opponent.makeMove(map);
        }
    }

    public void endGame() {
        if (moveCount % 2 == 0) {
            System.out.println("Поздравляем, вы выиграли!");
        } else {
            System.out.println("Вы проиграли.");
        }
        isNewGame();
    }

    public void draw() {
        System.out.println("Ничья!");
        isNewGame();
    }

    public void isNewGame() {
        System.out.print("Начать новую игру? (y/n): ");
        String answer = sc.nextLine();
        if (answer.equals("y")) {
            startGame();
        } else if (answer.equals("n")) {
            System.out.println();
        } else {
            isNewGame();
        }
    }
}
