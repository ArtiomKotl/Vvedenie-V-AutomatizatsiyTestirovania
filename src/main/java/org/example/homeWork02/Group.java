package org.example.homeWork02;

import java.util.ArrayList;
import java.util.List;

public class Group implements JournalComponent {
    private Teacher teacher;
    int index;
    private List<Student> students = new ArrayList<> ();

    public Group(int index) {
        this.index = index;
    }

    public void addStudent(Student student) {
        students.add ( student );
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public void print() {
        System.out.println ( "- Группа " + index + ". " + teacher.getName () + " " + teacher.getSurName () + ":" );
        for (Student student : students) {
            System.out.println ( "-- " + student.getName () + " " + student.getSurName () );
        }
    }
}
