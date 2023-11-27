package org.j120.lab1.linkedlists;

import org.j120.lab1.linkedlists.implementations.DoublyLinkedList;
import org.j120.lab1.linkedlists.implementations.SinglyLinkedList;
import org.j120.lab1.linkedlists.interfaces.CustomList;
import org.j120.lab1.linkedlists.interfaces.ExtendedCustomList;

public class Main {
    public static void main(String[] args) {
        CustomList<String> sll = new SinglyLinkedList<>();
        ExtendedCustomList<String> dll = new DoublyLinkedList<>();

        modifyCustomListOfStrings(sll);
        modifyCustomListOfStrings(dll);

        System.out.println("Do implementations of CustomList work identically: "
                + sll.toString().equals(dll.toString()));
    }

    public static void modifyCustomListOfStrings(CustomList<String> cl) {
        cl.addToBeginning("apples");
        cl.addToEnd("mangoes");
        cl.addToEnd("pomegranates");
        cl.addToEnd("lemons");
        cl.addToEnd("oranges");
        cl.deleteElement("lemons");
        cl.modifyAll(str -> "I love eating " + str);
        cl.removeFirst();
    }
}
