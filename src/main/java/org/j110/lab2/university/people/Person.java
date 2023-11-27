package org.j110.lab2.university.people;

import org.j110.lab2.university.enums.Gender;

import java.util.Collection;

public class Person {
    private String name;
    private Gender gender;
    private String department;

    public Person(String name, Gender gender, String department) {
        setName(name);
        setGender(gender);
        setDepartment(department);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null){
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        if (gender == null){
            throw new IllegalArgumentException("Gender cannot be null");
        }
        this.gender = gender;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        if (department == null){
            throw new IllegalArgumentException("Department cannot be null");
        }
        this.department = department;
    }

    public void print(){
        System.out.println(this);
    }
    public static void printAll(Person[] people){
        if (people == null){
            throw new IllegalArgumentException("Null array cannot be printed");
        }
        for (Person person : people){
            person.print();
        }
    }

    public static void printAll(Collection<? extends Person> people){
        if (people == null){
            throw new IllegalArgumentException("Collection is not initialized");
        }
        for (Person person : people){
            person.print();
        }
    }

    @Override
    public String toString() {
        return String.format("This is %s.", name);
    }
}
