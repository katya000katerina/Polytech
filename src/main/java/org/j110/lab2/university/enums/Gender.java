package org.j110.lab2.university.enums;

public enum Gender {
    MALE ("He", "His"),
    FEMALE("She", "Her");
    private final String subjectPronoun;
    private final String possessiveAdjective;

    Gender(String subjectPronoun, String possessiveAdjective) {
        this.subjectPronoun = subjectPronoun;
        this.possessiveAdjective = possessiveAdjective;
    }

    public String getSubjectPronoun() {
        return subjectPronoun;
    }

    public String getPossessiveAdjective() {
        return possessiveAdjective;
    }
}
