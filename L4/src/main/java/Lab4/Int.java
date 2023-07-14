package Lab4;

public class Int
{
    private int value;

    /**
     * Custom constructor
     * @param value Value of the Int
     */
    public Int(int value) {
        this.value = value;
    }

    /**
     * Default constructor (set value to 0)
     */
    public Int() { this.value = 0; }

    /**
     * Getter of the value
     * @return Value of the Int
     */
    public int getValue() {
        return value;
    }

    /**
     * Setter of the value
     * @param value
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * Return the value of the int in a string
     * @return String of the value
     */
    public String toString() {
        return String.valueOf(value);
    }

    /**
     * Compare two Int numbers
     * @param other
     * @return Difference between value of this - other
     */
    public int compareTo(Int other) { return this.value - other.getValue(); }

    /**
     * Swap two Int numbers
     * @param a
     * @param b
     */
    public static void swap(Int a, Int b) {
        int temp = a.value;
        a.value = b.value;
        b.value = temp;
    }

    /**
     * Swap this Int number with other Int
     * @param other
     */
    public void swap(Int other) {
        swap(this, other);
    }

    /**
     * Swap two Int numbers in a tab by index i and j
     * @param tab
     * @param i Index to swap
     * @param j Index to swap
     */
    public static void swap(Int[] tab, int i, int j) {
        swap(tab[i], tab[j]);
    }
}
