package org.j110.lab4;

public class IntegerArrayStoreBoolean implements BooleanCollection {
    private final int[] intArray = new int[SIZE / 32];

    @Override
    public boolean getByIndex(int booleanIndex) {
        validateIndex(booleanIndex);
        int intIndex = getIntIndex(booleanIndex);
        int mask = 1 << getMaskPosition(booleanIndex);
        return (intArray[intIndex] & mask) == mask;
    }

    @Override
    public void setTrue(int booleanIndex) {
        validateIndex(booleanIndex);
        int intIndex = getIntIndex(booleanIndex);
        int mask = 1 << getMaskPosition(booleanIndex);
        intArray[intIndex] |= mask;
    }

    @Override
    public void setFalse(int booleanIndex) {
        validateIndex(booleanIndex);
        int intIndex = getIntIndex(booleanIndex);
        int mask = ~(1 << getMaskPosition(booleanIndex));
        intArray[intIndex] &= mask;
    }

    @Override
    public void set(int booleanIndex, boolean value) {
        if (value) {
            setTrue(booleanIndex);
        } else {
            setFalse(booleanIndex);
        }
    }

    @Override
    public void invert(int booleanIndex) {
        validateIndex(booleanIndex);
        int intIndex = getIntIndex(booleanIndex);
        int mask = 1 << getMaskPosition(booleanIndex);
        intArray[intIndex] ^= mask;
    }

    @Override
    public int countTrue() {
        int count = 0;
        for (int num : intArray) {
            while (num != 0) {
                count += num & 1;
                num >>>= 1;
            }
        }
        return count;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int i = 0; i < SIZE; i++) {
            builder.append(getByIndex(i) ? "1" : "0");
            if (i == SIZE - 1) {
                return builder.append("]").toString();
            }
            builder.append(", ");
        }
        return "[]";
    }

    private int getIntIndex(int booleanIndex) {
        return booleanIndex / 32;
    }

    private int getMaskPosition(int booleanIndex) {
        return 31 - booleanIndex % 32;
    }

    private void validateIndex(int index) {
        if (index < 0 || index > 1023) {
            throw new IllegalArgumentException("Invalid index");
        }
    }
}
