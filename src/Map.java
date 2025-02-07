import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Map {
    private HashMap<Coordinates, Cell> map;
    private final int size;

    Map(int size) {
        this.size = size;
        initializeMap(size);
    }

    private void initializeMap(int size) {
        map = new HashMap<>();

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                map.put(new Coordinates(x,y), new Cell());
            }
        }
    }

    public void display() {
        System.out.println();
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                System.out.print(" " + map.get(new Coordinates(x,y)).getValue() + " "); // Отображаем значение ячейки
                if (y < size - 1) {
                    System.out.print("|"); // Вертикальная разделительная линия
                }
            }
            System.out.println(); // Переход на новую строку

            if (x < size - 1) {
                // Отображаем горизонтальную разделительную линию
                for (int z = 0; z < size; z++) {
                    System.out.print("----");
                    if (z < size - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println(); // Переход на новую строку
            }
        }
        System.out.println(); // Пустая строка для отделения от следующего вывода
    }

    private boolean arrayIsCorrect(List<Cell> arr) {
        // нет ни одной пустой клетки, и все равны
        return (arr.stream().noneMatch(Cell::isEmpty)) && (arr.stream().map(Cell::getValue).distinct().count() <= 1);
    }

    private boolean horizontalEquals() {
        for (int x = 0; x < size; x++) {
            List<Cell> horizontal = new ArrayList<>();
            for (int y = 0; y < size; y++) horizontal.add( map.get(new Coordinates(x,y)) );
            if (arrayIsCorrect(horizontal)) return true;
        }
        return false;
    }

    private boolean verticalEquals() {
        for (int y = 0; y < size; y++) {
            List<Cell> vertical = new ArrayList<>();
            for (int x = 0; x < size; x++) vertical.add( map.get(new Coordinates(x,y)) );
            if (arrayIsCorrect(vertical)) return true;
        }
        return false;
    }

    private boolean diagonalEquals() {
        List<Cell> diagonal = new ArrayList<>();
        List<Cell> antiDiagonal = new ArrayList<>();
        for (int x = 0; x < size; x++) {
            //noinspection SuspiciousNameCombination
            diagonal.add(map.get(new Coordinates(x,x)));
            antiDiagonal.add(map.get(new Coordinates(x, size - 1 - x)));
        }
        return arrayIsCorrect(diagonal) || arrayIsCorrect(antiDiagonal);
    }

    public boolean checkWinner() {
        return horizontalEquals() || verticalEquals() || diagonalEquals();
    }

    public boolean isFull() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (map.get(new Coordinates(x,y)).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void updateValue(Coordinates coordinates, String symbol) {
        map.get(coordinates).setValue(symbol);
        display();
    }

    public int getSize() {
        return size;
    }

    public Cell getCell(Coordinates coordinates) {
        return map.get(coordinates);
    }
}
