package org.j110.lab3.implementations;

import org.j110.lab3.interfaces.CustomList;

public class SinglyLinkedList implements CustomList {
    private ListNode head;
    private ListNode tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void addToBeginning(Integer element) {
        ListNode newNode = new ListNode(element);
        if (isEmpty()) {
            tail = newNode;
        }
        newNode.next = head;
        head = newNode;
    }

    public void addToEnd(Integer element) {
        ListNode newNode = new ListNode(element);
        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
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
        }
        head = head.next;
        return element;
    }

    public Integer removeLast() {
        if (isEmpty()) {
            return null;
        }
        Integer lastElement = tail.data;
        if (head == tail) {
            head = tail = null;
            return lastElement;
        }
        ListNode secondToLast = head;
        while (secondToLast.next != tail) {
            secondToLast = secondToLast.next;
        }
        secondToLast.next = null;
        tail = secondToLast;
        return lastElement;
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
        ListNode previous = null;
        while (current != null) {
            Integer data = current.data;
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

    public void printList() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        ListNode current = head;
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

    private static class ListNode {
        Integer data;
        ListNode next;

        ListNode(Integer data) {
            this.data = data;
        }
    }
}
