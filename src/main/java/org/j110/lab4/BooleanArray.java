package org.j110.lab4;

public class BooleanArray implements BooleanCollection {
    private final boolean[] array = new boolean[SIZE];

    @Override
    public boolean getByIndex(int booleanIndex) {
        validateIndex(booleanIndex);
        return array[booleanIndex];
    }

    @Override
    public void setTrue(int booleanIndex) {
        validateIndex(booleanIndex);
        array[booleanIndex] = true;
    }

    @Override
    public void setFalse(int booleanIndex) {
        validateIndex(booleanIndex);
        array[booleanIndex] = false;
    }

    @Override
    public void set(int booleanIndex, boolean value) {
        validateIndex(booleanIndex);
        array[booleanIndex] = value;
    }

    @Override
    public void invert(int booleanIndex) {
        validateIndex(booleanIndex);
        array[booleanIndex] = !array[booleanIndex];
    }

    @Override
    public int countTrue() {
        int count = 0;
        for (boolean value : array) {
            if (value) {
                count++;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < SIZE; i++) {
            builder.append(array[i] ? "1" : "0");
            if (i == SIZE - 1) {
                return builder.append("]").toString();
            }
            builder.append(", ");
        }
        return "[]";
    }

    private void validateIndex(int index) {
        if (index < 0 || index > array.length) {
            throw new IllegalArgumentException("Invalid index");
        }
    }
}
