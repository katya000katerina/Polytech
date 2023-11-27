package org.j120.lab1.linkedlists.implementations;


import org.j120.lab1.linkedlists.interfaces.ExtendedCustomList;

import java.util.Iterator;
import java.util.function.UnaryOperator;

public class DoublyLinkedList<T> implements ExtendedCustomList<T> {
    private ListNode<T> head;
    private ListNode<T> tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void addToBeginning(T element) {
        ListNode<T> newNode = new ListNode<>(element);
        linkToBeginning(newNode);
    }

    public void addToEnd(T element) {
        ListNode<T> newNode = new ListNode<>(element);
        linkToEnd(newNode);
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
        } else {
            head.next.previous = null;
        }
        head = head.next;
        return element;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T element = tail.data;
        if (head == tail) {
            head = null;
        } else {
            tail.previous.next = null;
        }
        tail = tail.previous;
        return element;
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
        while (current != null) {
            T data = current.data;
            if (data == null && element == null || data != null && data.equals(element)) {
                if (head == tail) {
                    head = tail = null;
                    return;
                } else if (current == head) {
                    head = head.next;
                    head.previous = null;
                } else {
                    current.previous.next = current.next;
                    if (current == tail) {
                        tail = current.previous;
                        return;
                    }
                    current.next.previous = current.previous;
                }
            }
            current = current.next;
        }
    }

    public void addAllToBeginning(T[] elements) {
        addAll(elements, true);
    }

    public void addAllToBeginning(Iterable<T> elements) {
        addAll(elements, true);
    }

    public void addAllToBeginning(DoublyLinkedList<T> dll) {
        addAll(dll, true);
    }

    public void addAllToEnd(T[] elements) {
        addAll(elements, false);
    }

    public void addAllToEnd(Iterable<T> elements) {
        addAll(elements, false);
    }

    public void addAllToEnd(DoublyLinkedList<T> dll) {
        addAll(dll, false);
    }

    public void modifyAll(UnaryOperator<T> operator) {
        ListNode<T> current = head;
        while (current != null) {
            current.data = operator.apply(current.data);
            current = current.next;
        }
    }

    public void modifyAllReverseOrder(UnaryOperator<T> operator) {
        ListNode<T> current = tail;
        while (current != null) {
            current.data = operator.apply(current.data);
            current = current.previous;
        }
    }

    public void printList() {
        System.out.println(this);
    }

    public void printListReverseOrder() {
        System.out.println(toStringReverseOrder());
    }

    @Override
    public String toString() {
        ListNode<T> current = head;
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (current != null) {
            builder.append(current.data);
            if (current == tail) {
                return builder.append("]").toString();
            }
            current = current.next;
            builder.append(", ");
        }
        return "[]";
    }

    public String toStringReverseOrder() {
        ListNode<T> current = tail;
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        while (current != null) {
            builder.append(current.data);
            if (current == head) {
                return builder.append("]").toString();
            }
            current = current.previous;
            builder.append(", ");
        }
        return "[]";
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    public Iterator<T> reverseIterator() {
        return new reverseListIterator();
    }

    public Iterator<T> iteratorFromHeadToElement(T element) {
        return new ListIteratorFromHeadToElement(element);
    }

    public Iterator<T> iteratorFromTailToElement(T element) {
        return new ListIteratorFromTailToElement(element);
    }

    public Iterator<T> iteratorFromElementToTail(T element) {
        return new ListIteratorFromElementToTail(element);
    }

    public Iterator<T> iteratorFromElementToHead(T element) {
        return new ListIteratorFromElementToHead(element);
    }

    private void addAll(T[] elements, boolean doAddToBeginning) {
        if (elements == null) {
            throw new IllegalArgumentException("Array is not initialized");
        }
        if (elements.length == 0) {
            return;
        }
        ListNode<T> firstNode = new ListNode<>(elements[0]);
        ListNode<T> lastNode = firstNode;
        for (int i = 1; i < elements.length; i++) {
            lastNode.next = new ListNode<>(elements[i]);
            lastNode.next.previous = lastNode;
            lastNode = lastNode.next;
        }
        linkNodes(firstNode, lastNode, doAddToBeginning);
    }

    private void addAll(Iterable<T> elements, boolean doAddToBeginning) {
        if (elements == null) {
            throw new IllegalArgumentException("Collection is not initialized");
        }
        Iterator<T> iterator = elements.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        ListNode<T> firstNode = new ListNode<>(iterator.next());
        ListNode<T> lastNode = firstNode;
        while (iterator.hasNext()) {
            lastNode.next = new ListNode<>(iterator.next());
            lastNode.next.previous = lastNode;
            lastNode = lastNode.next;
        }
        linkNodes(firstNode, lastNode, doAddToBeginning);
    }

    private void addAll(DoublyLinkedList<T> dll, boolean doAddToBeginning) {
        if (dll == null) {
            throw new IllegalArgumentException("Doubly linked list is not initialized");
        }
        if (dll.isEmpty() || dll == this) {
            return;
        }
        linkNodes(dll.head, dll.tail, doAddToBeginning);
        dll.head = dll.tail = null;
    }

    private void linkToBeginning(ListNode<T> node) {
        linkToBeginning(node, node);
    }

    private void linkToBeginning(ListNode<T> newHead, ListNode<T> lastNode) {
        if (isEmpty()) {
            tail = lastNode;
        } else {
            lastNode.next = head;
            head.previous = lastNode;
        }
        head = newHead;
    }

    private void linkToEnd(ListNode<T> node) {
        linkToEnd(node, node);
    }

    private void linkToEnd(ListNode<T> firstNode, ListNode<T> newTail) {
        if (isEmpty()) {
            head = firstNode;
        } else {
            firstNode.previous = tail;
            tail.next = firstNode;
        }
        tail = newTail;
    }

    private void linkNodes(ListNode<T> firstNode, ListNode<T> lastNode, boolean doAddToBeginning) {
        if (doAddToBeginning) {
            linkToBeginning(firstNode, lastNode);
        } else {
            linkToEnd(firstNode, lastNode);
        }
    }

    private static class ListNode<T> {
        T data;
        ListNode<T> previous;
        ListNode<T> next;

        public ListNode(T data) {
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

    private class reverseListIterator implements Iterator<T> {
        ListNode<T> current = tail;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.previous;
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

    private class ListIteratorFromTailToElement implements Iterator<T> {
        ListNode<T> current = tail;
        T lastElement;

        public ListIteratorFromTailToElement(T lastElement) {
            this.lastElement = lastElement;
        }

        @Override
        public boolean hasNext() {
            return current != null && !((current.data == null && lastElement == null) || current.data != null && current.data.equals(lastElement));
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.previous;
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

    private class ListIteratorFromElementToHead implements Iterator<T> {
        ListNode<T> current;

        public ListIteratorFromElementToHead(T firstElement) {
            current = tail;
            while (current != null) {
                T currentData = current.data;
                if (firstElement == null && currentData == null || currentData != null && currentData.equals(firstElement)) {
                    return;
                }
                current = current.previous;
            }
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.previous;
            return data;
        }
    }
}
