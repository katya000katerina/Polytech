package org.j110.lab2.university.people;

import org.j110.lab2.university.enums.Gender;

public class PostgraduateStudent extends Person{
    private String thesisTitle;

    public PostgraduateStudent(String name, Gender gender, String department, String thesisTitle) {
        super(name, gender, department);
        setThesisTitle(thesisTitle);
    }

    public String getThesisTitle() {
        return thesisTitle;
    }

    public void setThesisTitle(String thesisTitle) {
        if (thesisTitle == null){
            throw new IllegalArgumentException("Thesis title cannot be null");
        }
        this.thesisTitle = thesisTitle;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" %s studies at %s. %s thesis title is “%s”.",
                super.getGender().getSubjectPronoun(), super.getDepartment(),
                super.getGender().getPossessiveAdjective(), thesisTitle);
    }
}
