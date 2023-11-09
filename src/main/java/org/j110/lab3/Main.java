package org.j110.lab3;

import org.j110.lab3.implementations.DoublyLinkedList;
import org.j110.lab3.implementations.SinglyLinkedList;
import org.j110.lab3.implementations.UnrolledSinglyLinkedList;
import org.j110.lab3.interfaces.CustomList;
import org.j110.lab3.interfaces.ExtendedCustomList;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        CustomList sll = new SinglyLinkedList();
        ExtendedCustomList dll = new DoublyLinkedList();
        ExtendedCustomList usll = new UnrolledSinglyLinkedList();

        modifyCustomList(sll);
        modifyCustomList(dll);
        modifyCustomList(usll);

        System.out.println("Do implementations of CustomList work identically: "
                + (sll.toString().equals(usll.toString()) && usll.toString().equals(dll.toString())));

        modifyExtendedCustomList(dll);
        modifyExtendedCustomList(usll);

        System.out.println("Do implementations of ExtendedCustomList work identically: "
                + (dll.toString().equals(usll.toString())));

    }

    public static void modifyCustomList(CustomList cl){
        cl.addToBeginning(1);
        cl.addToEnd(2);
        cl.addToEnd(3);
        cl.addToEnd(4);
        cl.addToEnd(5);
        cl.incrementAllBy(5);
        cl.deleteElement(8);
        cl.removeFirst();
    }

    public static void modifyExtendedCustomList(ExtendedCustomList ecl){
        ecl.addAllToBeginning(new Integer[]{1,2,3,4});
        ecl.addAllToEnd(new ArrayList<>(Arrays.asList(5,6,7,8)));
    }
}
