package org.j110.lab2.university;

import org.j110.lab2.university.enums.*;
import org.j110.lab2.university.people.*;

public class Main {
    public static void main(String[] args) {
        Person[] people = new Person[]{
                new Professor("Ronald Turner", Gender.MALE, "Computer science", AcademicDegree.PHD, "Programming paradigms"),
                new Professor("Ruth Hollings", Gender.FEMALE, "Jurisprudence", AcademicDegree.MSC, "Domestic arbitration"),
                new Student("Leo Wilkinson", Gender.MALE, "Computer science", StudyLevel.BACHELOR, YearOfStudy.III),
                new Student("Anna Cunningham", Gender.FEMALE, "World economy", StudyLevel.BACHELOR, YearOfStudy.I),
                new Student("Jill Lundqvist", Gender.FEMALE, "Jurisprudence", StudyLevel.MASTER, YearOfStudy.I),
                new PostgraduateStudent("Ronald Correa", Gender.MALE, "Computer science", "Design of a functional programming language")
        };
        Person.printAll(people);
    }
}