package org.j110.lab2.university.people;

import org.j110.lab2.university.enums.AcademicDegree;
import org.j110.lab2.university.enums.Gender;

public class Professor extends Person{
    private AcademicDegree academicDegree;
    private String  specialtyName;

    public Professor(String name, Gender gender, String department, AcademicDegree academicDegree, String specialtyName) {
        super(name, gender, department);
        setAcademicDegree(academicDegree);
        setSpecialtyName(specialtyName);
    }

    public AcademicDegree getAcademicDegree() {
        return academicDegree;
    }

    public void setAcademicDegree(AcademicDegree academicDegree) {
        if (academicDegree == null){
            throw new IllegalArgumentException("Academic degree cannot be null");
        }
        this.academicDegree = academicDegree;
    }

    public String getSpecialtyName() {
        return specialtyName;
    }

    public void setSpecialtyName(String specialtyName) {
        if (specialtyName == null){
            throw new IllegalArgumentException("Specialty name cannot be null");
        }
        this.specialtyName = specialtyName;
    }

    @Override
    public String toString() {
        return super.toString() +
                String.format(" %s teaches at %s. %1$s has %s in %s.",
                        super.getGender().getSubjectPronoun(), super.getDepartment(), academicDegree, specialtyName);
    }
}
