package org.j110.lab2.university;

import org.j110.lab2.university.enums.AcademicDegree;
import org.j110.lab2.university.enums.Gender;
import org.j110.lab2.university.people.Person;
import org.j110.lab2.university.people.Professor;
import org.j110.lab2.university.people.Student;

import java.util.LinkedList;
import java.util.List;

public class MainJ120 {
    public static void main(String[] args) {
        System.out.println("j120_1_3 demonstration\n");
        List<Person> persons = new LinkedList<>();

        persons.add(new Person("Richard Butler", Gender.MALE, "Computer science"));
        persons.add(new Person("Lisa Clarke", Gender.FEMALE, "Jurisprudence"));
        persons.add(new Person("Brian Evans", Gender.MALE, "World economy"));

        Person.printAll(persons);

        List<Professor> professors = new LinkedList<>();

        professors.add(new Professor("James Williams", Gender.MALE, "Computer science", AcademicDegree.MSC, "Discrete mathematics"));
        professors.add(new Professor("Laura Martin", Gender.FEMALE, "World economy", AcademicDegree.PHD, "Political economy"));
        professors.add(new Professor("Karen Lopez", Gender.FEMALE, "World economy", AcademicDegree.BSC, "Behavioral economics"));

        Person.printAll(professors);

        System.out.println("\nj120_1_4 demonstration\n");
        List<Student> students = new LinkedList<>();
        Student.fillCollection(students);

        List<Person> persons2 = new LinkedList<>();
        Student.fillCollection(persons2);

        System.out.println("Do both collections hold identical objects: " + students.toString().equals(persons2.toString()));
    }
}
