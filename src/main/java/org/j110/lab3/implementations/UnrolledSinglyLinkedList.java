package org.j110.lab3.implementations;

import org.j110.lab3.interfaces.ExtendedCustomList;

import java.util.Iterator;

public class UnrolledSinglyLinkedList implements ExtendedCustomList {
    private ListNode head;
    private ListNode tail;

    public boolean isEmpty() {
        return head == null;
    }

    public void addToBeginning(Integer element) {
        if (isEmpty()) {
            head = tail = new ListNode(element);
            return;
        }
        if (head.numElements == ListNode.NODE_CAPACITY) {
            moveHalfToNext(head);
        }
        System.arraycopy(head.dataArray, 0, head.dataArray, 1, head.numElements++);
        head.dataArray[0] = element;
    }

    public void addToEnd(Integer element) {
        if (isEmpty()) {
            head = tail = new ListNode(element);
            return;
        }
        if (tail.numElements == ListNode.NODE_CAPACITY) {
            moveHalfToNext(tail);
        }
        tail.dataArray[tail.numElements++] = element;
    }

    public void addAllToBeginning(Integer[] elements) {
        addAll(elements, true);
    }

    public void addAllToBeginning(Iterable<Integer> elements) {
        addAll(elements, true);
    }

    public void addAllToBeginning(UnrolledSinglyLinkedList usll) {
        addAll(usll, true);
    }

    public void addAllToEnd(Integer[] elements) {
        addAll(elements, false);
    }

    public void addAllToEnd(Iterable<Integer> elements) {
        addAll(elements, false);
    }

    public void addAllToEnd(UnrolledSinglyLinkedList usll) {
        addAll(usll, false);
    }

    public Integer getFirst() {
        return !isEmpty() ? head.dataArray[0] : null;
    }

    public Integer getLast() {
        return !isEmpty() ? tail.dataArray[tail.numElements - 1] : null;
    }

    public Integer removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Integer element = head.dataArray[0];
        System.arraycopy(head.dataArray, 1, head.dataArray, 0, --head.numElements);
        head.dataArray[head.numElements] = null;
        if (head.numElements == 0) {
            head = head.next;
        }
        return element;
    }

    public Integer removeLast() {
        if (isEmpty()) {
            return null;
        }
        Integer element = tail.dataArray[tail.numElements--];
        tail.dataArray[tail.numElements] = null;
        if (tail.numElements == 0) {
            ListNode secondToLast = head;
            while (secondToLast.next != tail) {
                secondToLast = secondToLast.next;
            }
            secondToLast.next = null;
            tail = secondToLast;
        }
        return element;
    }

    public boolean doesContainElement(Integer element) {
        if (isEmpty()) {
            return false;
        }
        ListNode current = head;
        while (current != null) {
            for (int i = 0; i < current.numElements; i++) {
                Integer data = current.dataArray[i];
                if (data == null && element == null || data != null && data.equals(element)) {
                    return true;
                }
            }
            current = current.next;
        }
        return false;
    }

    public void deleteElement(Integer element) {
        ListNode current = head;
        ListNode previous = null;

        outerLoop:
        while (current != null) {
            Integer[] arr = current.dataArray;
            for (int i = 0; i < current.numElements; ) {
                Integer data = arr[i];
                if (data == null && element == null || data != null && data.equals(element)) {
                    if (current.numElements == 1) {
                        deleteNode(previous, current);
                        current = current.next;
                        continue outerLoop;
                    } else {
                        if (current.numElements < ListNode.NODE_CAPACITY) {
                            System.arraycopy(arr, i + 1, arr, i, current.numElements - i - 1);
                        }
                        arr[--current.numElements] = null;
                        continue;
                    }
                }
                i++;
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
            for (int i = 0; i < current.numElements; i++) {
                if (current.dataArray[i] == null) {
                    continue;
                }
                current.dataArray[i] += num;
            }
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
            Integer[] arr = current.dataArray;
            int numElements = current.numElements;
            for (int i = 0; i < numElements; i++) {
                builder.append(arr[i]);
                if (current == tail && i == numElements - 1) {
                    return builder.append("]").toString();
                }
                builder.append(", ");
            }
            current = current.next;
        }
        return "[]";
    }

    private ListNode moveHalfToNext(ListNode node) {
        ListNode newNode;
        if (node.next != null && node.next.numElements < ListNode.HALF_NODE_CAPACITY) {
            newNode = node.next;
        } else {
            newNode = new ListNode();
            newNode.next = node.next;
            node.next = newNode;
            if (node == tail) {
                tail = newNode;
            }
        }
        System.arraycopy(node.dataArray, ListNode.HALF_NODE_CAPACITY, newNode.dataArray, newNode.numElements, ListNode.HALF_NODE_CAPACITY);
        System.arraycopy(new Integer[ListNode.HALF_NODE_CAPACITY], 0, node.dataArray, ListNode.HALF_NODE_CAPACITY, ListNode.HALF_NODE_CAPACITY);
        newNode.numElements += ListNode.HALF_NODE_CAPACITY;
        node.numElements = ListNode.HALF_NODE_CAPACITY;
        return newNode;
    }

    private void deleteNode(ListNode previous, ListNode current) {
        if (head == tail && current == head) {
            head = tail = null;
        } else if (current == head) {
            head = head.next;
        } else {
            previous.next = current.next;
            if (current == tail) {
                tail = previous;
            }
        }
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
            if (lastNode.numElements == ListNode.NODE_CAPACITY) {
                lastNode = moveHalfToNext(lastNode);
            }
            lastNode.dataArray[lastNode.numElements++] = elements[i];
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
            if (lastNode.numElements == ListNode.NODE_CAPACITY) {
                lastNode = moveHalfToNext(lastNode);
            }
            lastNode.dataArray[lastNode.numElements++] = iterator.next();
        }
        linkNodes(firstNode, lastNode, doAddToBeginning);
    }

    private void addAll(UnrolledSinglyLinkedList usll, boolean doAddToBeginning) {
        if (usll == null) {
            throw new IllegalArgumentException("Unrolled singly linked list is not initialized");
        }
        if (usll.isEmpty() || usll == this) {
            return;
        }
        linkNodes(usll.head, usll.tail, doAddToBeginning);
        usll.head = usll.tail = null;
    }

    private void linkNodesToBeginning(ListNode newHead, ListNode lastNode) {
        if (isEmpty()) {
            tail = lastNode;
        } else {
            lastNode.next = head;
        }
        head = newHead;
    }

    private void linkNodesToEnd(ListNode firstNode, ListNode newTail) {
        if (isEmpty()) {
            head = firstNode;
        } else {
            tail.next = firstNode;
        }
        tail = newTail;
    }

    private void linkNodes(ListNode firstNode, ListNode lastNode, boolean doAddToBeginning) {
        if (doAddToBeginning) {
            linkNodesToBeginning(firstNode, lastNode);
        } else {
            linkNodesToEnd(firstNode, lastNode);
        }
    }

    private static class ListNode {
        static final int NODE_CAPACITY = 10;
        static final int HALF_NODE_CAPACITY = NODE_CAPACITY / 2;
        Integer[] dataArray;
        int numElements = 0;
        ListNode next;

        public ListNode() {
            dataArray = new Integer[NODE_CAPACITY];
        }

        public ListNode(Integer data) {
            dataArray = new Integer[NODE_CAPACITY];
            dataArray[numElements++] = data;
        }
    }
}
