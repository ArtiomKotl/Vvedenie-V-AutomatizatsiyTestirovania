package org.example.homeWork02;

import java.util.ArrayList;
import java.util.List;

public class Composite implements JournalComponent {
    List<JournalComponent> children = new ArrayList<> ();

    public void add(JournalComponent component) {
        if (component == null) {
            throw new NullPointerException ( "Component cannot be null" );
        }

        children.add ( component );

    }

    public void remove(JournalComponent component) {
        if (!children.contains ( component )) {
            throw new IllegalArgumentException ( "Component not found" );
        }

        children.remove ( component );
    }

    public JournalComponent getChild(int index) {
        try {
            return children.get ( index );
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException ( "Invalid child index" );
        }
    }


    @Override
    public void print() {
        for (JournalComponent component : children) {
            component.print ();
        }

    }


}
