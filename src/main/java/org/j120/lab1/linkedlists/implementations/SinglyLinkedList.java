package org.j120.lab1.linkedlists.implementations;


import org.j120.lab1.linkedlists.interfaces.CustomList;

import java.util.Iterator;
import java.util.function.UnaryOperator;

public class SinglyLinkedList<T> implements CustomList<T> {
    private ListNode<T> head;
    private ListNode<T> tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void addToBeginning(T element) {
        ListNode<T> newNode = new ListNode<>(element);
        if (isEmpty()) {
            tail = newNode;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addToEnd(T element) {
        ListNode<T> newNode = new ListNode<>(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    public T getFirst() {
        return !isEmpty() ? head.data : null;
    }

    public T getLast() {
        return !isEmpty() ? tail.data : null;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T element = head.data;
        if (head == tail) {
            tail = null;
        }
        head = head.next;
        return element;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T lastElement = tail.data;
        if (head == tail) {
            head = tail = null;
            return lastElement;
        }
        ListNode<T> secondToLast = head;
        while (secondToLast.next != tail) {
            secondToLast = secondToLast.next;
        }
        secondToLast.next = null;
        tail = secondToLast;
        return lastElement;
    }

    public boolean doesContainElement(T element) {
        if (isEmpty()) {
            return false;
        }
        ListNode<T> current = head;
        while (current != null) {
            T data = current.data;
            if (data == null && element == null || data != null && data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void deleteElement(T element) {
        if (isEmpty()) {
            return;
        }
        ListNode<T> current = head;
        ListNode<T> previous = null;
        while (current != null) {
            T data = current.data;
            if (data == null && element == null || data != null && data.equals(element)) {
                if (head == tail) {
                    head = tail = null;
                    return;
                } else if (current == head) {
                    head = current.next;
                    current = head;
                    continue;
                } else {
                    previous.next = current.next;
                    if (current == tail) {
                        tail = previous;
                        return;
                    }
                    current = current.next;
                    continue;
                }
            }
            previous = current;
            current = current.next;
        }
    }

    public void modifyAll(UnaryOperator<T> operator) {
        ListNode<T> current = head;
        while (current != null) {
            current.data = operator.apply(current.data);
            current = current.next;
        }
    }

    public void printList() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        ListNode<T> current = head;
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (current != null) {
            builder.append(current.data);
            if (current.next == null) {
                return builder.append("]").toString();
            }
            current = current.next;
            builder.append(", ");
        }
        return "[]";
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    public Iterator<T> iteratorFromHeadToElement(T element) {
        return new ListIteratorFromHeadToElement(element);
    }

    public Iterator<T> iteratorFromElementToTail(T element) {
        return new ListIteratorFromElementToTail(element);
    }

    private static class ListNode<T> {
        T data;
        ListNode<T> next;

        ListNode(T data) {
            this.data = data;
        }
    }

    private class ListIterator implements Iterator<T> {
        ListNode<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    private class ListIteratorFromHeadToElement implements Iterator<T> {
        ListNode<T> current = head;
        T lastElement;

        public ListIteratorFromHeadToElement(T lastElement) {
            this.lastElement = lastElement;
        }

        @Override
        public boolean hasNext() {
            return current != null && !((current.data == null && lastElement == null) || current.data != null && current.data.equals(lastElement));
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    private class ListIteratorFromElementToTail implements Iterator<T> {
        ListNode<T> current;

        public ListIteratorFromElementToTail(T firstElement) {
            current = head;
            while (current != null) {
                T currentData = current.data;
                if (firstElement == null && currentData == null || currentData != null && currentData.equals(firstElement)) {
                    return;
                }
                current = current.next;
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }
}
