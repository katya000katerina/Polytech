package org.j110.lab4;

public interface BooleanCollection {
    int SIZE = 1024;

    boolean getByIndex(int booleanIndex);

    void setTrue(int booleanIndex);

    void setFalse(int booleanIndex);

    void set(int booleanIndex, boolean value);

    void invert(int booleanIndex);

    int countTrue();
}
