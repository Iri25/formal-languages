package Lab23.Model;

import java.util.ArrayList;
import java.util.Arrays;

public class TAD {

    private int maxSize;
    private int actualSize;
    private TS[] array;

    /**
     * Constructor
     * @param maxSize - the maximum size of the symbol table
     */
    public TAD(int maxSize) {
        this.maxSize = maxSize;
        this.actualSize = 0;
        this.array = new TS[maxSize];
    }

    /**
     * Function for determining the size of the symbol table
     * @return  the size of the symbol table
     */
    public int size() {
        return actualSize;
    }

    /**
     * Function of returning an item from a certain position in the symbol table
     * @param index - desired position
     * @return the item on the desired position in the symbol table
     */
    public TS get(int index) {
        return array[index];
    }

    /**
     * Symbol table resize function in case of overflow
     */
    private void resize() {
        TS[] nou = new TS[2 * maxSize];
        if (maxSize >= 0)
            System.arraycopy(this.array, 0, nou, 0, maxSize);
        this.maxSize = 2 * this.maxSize;
        this.array = nou;
    }

    /**
     * Function to add an item to the TAD list
     * @param position - the position to which it will be added
     * @param element - item to add
     */
    public void add(int position, TS element) {
        if (actualSize == maxSize) resize();
        if (actualSize - position >= 0)
            System.arraycopy(array, position, array, position + 1, actualSize - position);
        this.actualSize++;
        array[position] = element;
    }

    /**
     * TS array table return function
     * @return table TS as array
     */
    public ArrayList<TS> getArray() {
        return new ArrayList<>(Arrays.asList(array).subList(0, this.actualSize));
    }

}
