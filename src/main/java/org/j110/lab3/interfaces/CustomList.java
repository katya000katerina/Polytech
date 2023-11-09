package org.j110.lab3.interfaces;

public interface CustomList {
    boolean isEmpty();

    void addToBeginning(Integer element);

    void addToEnd(Integer element);

    Integer getFirst();

    Integer getLast();

    Integer removeFirst();

    Integer removeLast();

    boolean doesContainElement(Integer element);

    void deleteElement(Integer element);

    void printList();

    void incrementAllBy(Integer num);
}
