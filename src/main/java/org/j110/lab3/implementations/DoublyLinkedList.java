package org.j110.lab3.implementations;

import org.j110.lab3.interfaces.ExtendedCustomList;

import java.util.Iterator;

public class DoublyLinkedList implements ExtendedCustomList {
    private ListNode head;
    private ListNode tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void addToBeginning(Integer element) {
        ListNode newNode = new ListNode(element);
        linkToBeginning(newNode);
    }

    public void addToEnd(Integer element) {
        ListNode newNode = new ListNode(element);
        linkToEnd(newNode);
    }

    public Integer getFirst() {
        return !isEmpty() ? head.data : null;
    }

    public Integer getLast() {
        return !isEmpty() ? tail.data : null;
    }

    public Integer removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Integer element = head.data;
        if (head == tail) {
            tail = null;
        } else {
            head.next.previous = null;
        }
        head = head.next;
        return element;
    }

    public Integer removeLast() {
        if (isEmpty()) {
            return null;
        }
        Integer element = tail.data;
        if (head == tail) {
            head = null;
        } else {
            tail.previous.next = null;
        }
        tail = tail.previous;
        return element;
    }

    public boolean doesContainElement(Integer element) {
        if (isEmpty()) {
            return false;
        }
        ListNode current = head;
        while (current != null) {
            Integer data = current.data;
            if (data == null && element == null || data != null && data.equals(element)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }

    public void deleteElement(Integer element) {
        if (isEmpty()) {
            return;
        }
        ListNode current = head;
        while (current != null) {
            Integer data = current.data;
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

    public void addAllToBeginning(Integer[] elements) {
        addAll(elements, true);
    }

    public void addAllToBeginning(Iterable<Integer> elements) {
        addAll(elements, true);
    }

    public void addAllToBeginning(DoublyLinkedList dll) {
        addAll(dll, true);
    }

    public void addAllToEnd(Integer[] elements) {
        addAll(elements, false);
    }

    public void addAllToEnd(Iterable<Integer> elements) {
        addAll(elements, false);
    }

    public void addAllToEnd(DoublyLinkedList dll) {
        addAll(dll, false);
    }

    public void incrementAllBy(Integer num) {
        if (num == null) {
            return;
        }
        ListNode current = head;
        while (current != null) {
            if (current.data == null) {
                current = current.next;
                continue;
            }
            current.data += num;
            current = current.next;
        }
    }

    public void incrementInReverseOrderAllBy(Integer num) {
        if (num == null) {
            return;
        }
        ListNode current = tail;
        while (current != null) {
            if (current.data == null) {
                current = current.previous;
                continue;
            }
            current.data += num;
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
        ListNode current = head;
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
        ListNode current = tail;
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

    private void addAll(Integer[] elements, boolean doAddToBeginning) {
        if (elements == null) {
            throw new IllegalArgumentException("Array is not initialized");
        }
        if (elements.length == 0) {
            return;
        }
        ListNode firstNode = new ListNode(elements[0]);
        ListNode lastNode = firstNode;
        for (int i = 1; i < elements.length; i++) {
            lastNode.next = new ListNode(elements[i]);
            lastNode.next.previous = lastNode;
            lastNode = lastNode.next;
        }
        linkNodes(firstNode, lastNode, doAddToBeginning);
    }

    private void addAll(Iterable<Integer> elements, boolean doAddToBeginning) {
        if (elements == null) {
            throw new IllegalArgumentException("Collection is not initialized");
        }
        Iterator<Integer> iterator = elements.iterator();
        if (!iterator.hasNext()) {
            return;
        }
        ListNode firstNode = new ListNode(iterator.next());
        ListNode lastNode = firstNode;
        while (iterator.hasNext()) {
            lastNode.next = new ListNode(iterator.next());
            lastNode.next.previous = lastNode;
            lastNode = lastNode.next;
        }
        linkNodes(firstNode, lastNode, doAddToBeginning);
    }

    private void addAll(DoublyLinkedList dll, boolean doAddToBeginning) {
        if (dll == null) {
            throw new IllegalArgumentException("Doubly linked list is not initialized");
        }
        if (dll.isEmpty() || dll == this) {
            return;
        }
        linkNodes(dll.head, dll.tail, doAddToBeginning);
        dll.head = dll.tail = null;
    }

    private void linkToBeginning(ListNode node) {
        linkToBeginning(node, node);
    }

    private void linkToBeginning(ListNode newHead, ListNode lastNode) {
        if (isEmpty()) {
            tail = lastNode;
        } else {
            lastNode.next = head;
            head.previous = lastNode;
        }
        head = newHead;
    }

    private void linkToEnd(ListNode node) {
        linkToEnd(node, node);
    }

    private void linkToEnd(ListNode firstNode, ListNode newTail) {
        if (isEmpty()) {
            head = firstNode;
        } else {
            firstNode.previous = tail;
            tail.next = firstNode;
        }
        tail = newTail;
    }

    private void linkNodes(ListNode firstNode, ListNode lastNode, boolean doAddToBeginning) {
        if (doAddToBeginning) {
            linkToBeginning(firstNode, lastNode);
        } else {
            linkToEnd(firstNode, lastNode);
        }
    }

    private static class ListNode{
        Integer data;
        ListNode previous;
        ListNode next;

        public ListNode(Integer data) {
            this.data = data;
        }
    }
}
