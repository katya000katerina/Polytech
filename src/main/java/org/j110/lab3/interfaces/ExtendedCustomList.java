package org.j110.lab3.interfaces;

public interface ExtendedCustomList extends CustomList {
    void addAllToBeginning(Integer[] elements);

    void addAllToBeginning(Iterable<Integer> elements);

    void addAllToEnd(Integer[] elements);

    void addAllToEnd(Iterable<Integer> elements);

}
