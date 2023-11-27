package org.j120.lab1.linkedlists.interfaces;

import java.util.Iterator;
import java.util.function.UnaryOperator;

public interface CustomList<T> extends Iterable<T> {
    boolean isEmpty();

    void addToBeginning(T element);

    void addToEnd(T element);

    T getFirst();

    T getLast();

    T removeFirst();

    T removeLast();

    boolean doesContainElement(T element);

    void deleteElement(T element);

    void printList();

    void modifyAll(UnaryOperator<T> operator);

    Iterator<T> iteratorFromHeadToElement(T element);

    Iterator<T> iteratorFromElementToTail(T element);
}
