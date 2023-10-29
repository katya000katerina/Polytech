package org.j110.lab2.university.people;

import org.j110.lab2.university.enums.Gender;
import org.j110.lab2.university.enums.StudyLevel;
import org.j110.lab2.university.enums.YearOfStudy;

public class Student extends Person{
    private StudyLevel studyLevel;
    private YearOfStudy yearOfStudy;


    public Student(String name, Gender gender, String department, StudyLevel studyLevel, YearOfStudy yearOfStudy) {
        super(name, gender, department);
        setStudyLevel(studyLevel);
        setYearOfStudy(yearOfStudy);
    }
    public StudyLevel getStudyLevel() {
        return studyLevel;
    }

    public void setStudyLevel(StudyLevel studyLevel) {
        if (studyLevel == null){
            throw new IllegalArgumentException("Study level cannot be null");
        }
        this.studyLevel = studyLevel;
    }

    public YearOfStudy getYearOfStudy() {
        return yearOfStudy;
    }

    public void setYearOfStudy(YearOfStudy yearOfStudy) {
        if (yearOfStudy == null){
            throw new IllegalArgumentException("Year of study cannot be null");
        }
        if (studyLevel == StudyLevel.MASTER && yearOfStudy.ordinal() > 1){
            throw new IllegalArgumentException("A master student cannot study for more than 2 years");
        }
        this.yearOfStudy = yearOfStudy;
    }
    @Override
    public String toString() {
        return super.toString() +
                String.format(" %s studies at %s. %1$s is a %s year %s student.",
                        super.getGender().getSubjectPronoun(), super.getDepartment(), yearOfStudy.getOrdinalNumber(),
                        studyLevel.name().toLowerCase());
    }
}
