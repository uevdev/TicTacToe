import java.util.Arrays;
import java.util.Objects;

public class Coordinates {
    private final int[] coordinates;
    private final int x;
    private final int y;


    Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
        coordinates = new int[] {x,y};
    }


    public int getX() {
        return x;
    }


    public int getY() {
        return y;
    }



    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return this.getX() == that.getX() && this.getY() == that.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(Arrays.hashCode(coordinates), x, y);
    }
}
