package org.j120.lab1.phonenumber;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        PhoneNumber pn1 = new PhoneNumber(123, 590021);
        PhoneNumber pn2 = new PhoneNumber(1457, 245890);
        PhoneNumber pn3 = new PhoneNumber(4680, 267479);
        PhoneNumber pn4 = new PhoneNumber(986, 2468953);
        PhoneNumber pn5 = new PhoneNumber(986, 2468953);

        Set<PhoneNumber> set1 = new HashSet<>();
        Set<PhoneNumber> set2 = new HashSet<>();

        set1.add(pn1);
        set1.add(pn2);
        set1.add(pn3);
        set1.add(pn4);

        set2.addAll(set1);
        set2.add(pn5);

        System.out.println("Was number " + pn5 + " put into set2 for the second time: " +
                (set2.size() == 5 ? "yes. Something is wrong." : "no. Everything works correctly."));

        Map<PhoneNumber, String> map = new HashMap<>();

        map.put(pn1, "Emma");
        map.put(pn2, "Lucas");
        map.put(pn3, "Evelyn");
        map.put(pn4, "Henry");
        map.put(pn5, "James");

        System.out.println("Does number " + pn4 + " still belong to Henry: " +
                ("Henry".equals(map.get(pn4)) ? "yes. Something is wrong." : "no. Everything works correctly."));
    }
}
