package org.j120.lab2.frequencydictionary;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        String sep = File.separator;
        RussianFrequencyDictionary fd = new RussianFrequencyDictionary("С:" + sep + "texts",
                "С:" + sep + "texts" + sep + "text1.txt", "С:" + sep + "texts" + sep + "text2.txt");
        fd.generateReportByAlphabeticalOrder();
        fd.generateReportByLastLettersAlphabeticalOrder();
        fd.generateReportByFrequency();
    }
}
