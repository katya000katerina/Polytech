package org.j120.lab1.linkedlists.interfaces;

import java.util.Iterator;

public interface ExtendedCustomList<T> extends CustomList<T>, Iterable<T> {
    void addAllToBeginning(T[] elements);

    void addAllToBeginning(Iterable<T> elements);

    void addAllToEnd(T[] elements);

    void addAllToEnd(Iterable<T> elements);

    Iterator<T> reverseIterator();

    Iterator<T> iteratorFromTailToElement(T element);

    Iterator<T> iteratorFromElementToHead(T element);
}
