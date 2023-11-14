package org.j110.lab4;

public class Main {
    public static void main(String[] args) {
        BooleanCollection ba = new BooleanArray();
        BooleanCollection ia = new IntegerArrayStoreBoolean();

        modifyBooleanCollection(ba);
        modifyBooleanCollection(ia);

        System.out.println("Do implementations work identically: " +
                (ba.toString().equals(ia.toString()) &&
                        (ba.countTrue() == ia.countTrue()) &&
                        (ba.getByIndex(349)) == ia.getByIndex(349)));
    }
    public static void modifyBooleanCollection(BooleanCollection collection){
        collection.setTrue(678);
        collection.setFalse(145);
        collection.invert(12);
        collection.set(87, true);
    }
}
