public class Cell {
    private String value;

    Cell() {
        this.value = "  ";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isEmpty() {
        return value.trim().isEmpty();
    }
}
