package org.example.homeWork02;

import java.util.ArrayList;
import java.util.List;

public class Stream implements JournalComponent {

    private int index;

    private List<JournalComponent> groups = new ArrayList<> ();

    public Stream(int index) {
        this.index = index;
    }

    public void addGroup(JournalComponent group) {
        groups.add ( group );
    }

    public void print() {
        System.out.println ( "Поток " + index + ":" );
        for (JournalComponent group : groups) {
            group.print ();
        }
    }

}
