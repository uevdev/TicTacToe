import java.util.ArrayList;

public class Map {
    private ArrayList<ArrayList<Cell>> map;
    private final int size;

    Map(int size) {
        this.size = size;
        initializeMap(size);
    }

    private void initializeMap(int size) {
        map = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            ArrayList<Cell> currLine = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                currLine.add(new Cell());
            }
            map.add( currLine );
        }
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(" " + map.get(i).get(j).getValue() + " "); // Отображаем значение ячейки
                if (j < size - 1) {
                    System.out.print("|"); // Вертикальная разделительная линия
                }
            }
            System.out.println(); // Переход на новую строку

            if (i < size - 1) {
                // Отображаем горизонтальную разделительную линию
                for (int j = 0; j < size; j++) {
                    System.out.print("----");
                    if (j < size - 1) {
                        System.out.print("+");
                    }
                }
                System.out.println(); // Переход на новую строку
            }
        }
        System.out.println(); // Пустая строка для отделения от следующего вывода
    }

    private boolean arrayIsCorrect(ArrayList<Cell> arr) {
        // нет ни одной пустой клетки, и все равны
        return (arr.stream().noneMatch(Cell::isEmpty)) && (arr.stream().map(Cell::getValue).distinct().count() <= 1);
    }

    private boolean horizontalEquals() {
        return map.stream().anyMatch(this::arrayIsCorrect);
    }

    private boolean verticalEquals() {
        for (int i = 0; i < size; i++) {
            ArrayList<Cell> column = new ArrayList<>();
            for (ArrayList<Cell> j : map) {
                column.add(j.get(i));
            }
            if (arrayIsCorrect(column)) {
                return true;
            }
        }
        return false;
    }

    private boolean diagonalEquals() {
        ArrayList<Cell> diagonal = new ArrayList<>();
        ArrayList<Cell> antiDiagonal = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            diagonal.add(map.get(i).get(i));
            antiDiagonal.add(map.get(i).get(size - 1 - i));
        }
        return arrayIsCorrect(diagonal) || arrayIsCorrect(antiDiagonal);
    }

    public boolean checkWinner() {
        return horizontalEquals() || verticalEquals() || diagonalEquals();
    }

    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (map.get(i).get(j).isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void updateValue(String symbol, int i, int j) {
        map.get(i).get(j).setValue(symbol);
        display();
    }

    public int getSize() {
        return size;
    }

    public Cell getCell(int i, int j) {
        return map.get(i).get(j);
    }
}
