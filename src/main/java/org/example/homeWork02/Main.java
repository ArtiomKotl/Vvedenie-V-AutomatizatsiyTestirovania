package org.example.homeWork02;

public class Main {
    public static void main(String[] args) {
        JournalComponent group1 = new Group ( 1 );
        ((Group) group1).setTeacher ( new Teacher ( "Доцент", "Викторов" ) );
        ((Group) group1).addStudent ( new Student ( "Петр", "Петров" ) );

        JournalComponent group2 = new Group ( 2 );
        ((Group) group2).setTeacher ( new Teacher ( "Владимир", "Павлов" ) );
        ((Group) group2).addStudent ( new Student ( "Иван", "Иванов" ) );
        ((Group) group2).addStudent ( new Student ( "Сергей", "Сергеев" ) );
        ((Group) group2).addStudent ( new Student ( "Игорь", "Игорев" ) );
        ((Group) group2).addStudent ( new Student ( "Роман", "Романов" ) );
        ((Group) group2).addStudent ( new Student ( "Татьяна", "Иванова" ) );

        JournalComponent stream = new Stream ( 1 );
        ((Stream) stream).addGroup ( group1 );
        ((Stream) stream).addGroup ( group2 );

        JournalComponent faculty = new Composite ();
        ((Composite) faculty).add ( stream );


        faculty.print ();


    }

}
