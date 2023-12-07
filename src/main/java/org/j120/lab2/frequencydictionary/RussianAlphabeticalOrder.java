package org.j120.lab2.frequencydictionary;

import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Comparator;

public class RussianAlphabeticalOrder implements Comparator<String> {
    private final static String RUSSIAN_ALPHABET = "< а, А < б, Б < в, В < г, Г < д, Д < е, Е < ё, Ё < ж, Ж < з, З < и, И < й, Й < к, К " +
            "< л, Л < м, М < н, Н < о, О < п, П < р, Р < с, С < т, Т < у, У < ф, Ф < х, Х < ц, Ц < ч, Ч " +
            "< ш, Ш < щ, Щ < ъ, Ъ < ы, Ы < ь, Ь < э, Э < ю, Ю < я, Я";
    private final RuleBasedCollator collator = new RuleBasedCollator(RUSSIAN_ALPHABET);

    public RussianAlphabeticalOrder() throws ParseException {
    }

    @Override
    public int compare(String o1, String o2) {
        return collator.compare(o1, o2);
    }
}
