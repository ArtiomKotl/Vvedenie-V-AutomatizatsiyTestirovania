package org.example.homeWork02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class CompositeTest {

    private Composite composite;

    @BeforeEach
    public void setUp() {
        composite = new Composite ();
    }

    @Test
    public void testAdd() {
        JournalComponent child = mock ( JournalComponent.class );

        composite.add ( child );

        assertEquals ( 1, composite.children.size () );
        assertSame ( child, composite.getChild ( 0 ) );
    }

    @Test
    public void testRemove() {
        JournalComponent child = mock ( JournalComponent.class );

        composite.add ( child );
        composite.remove ( child );

        assertEquals ( 0, composite.children.size () );
    }

    @Test
    public void testPrint() {
        JournalComponent child1 = mock ( JournalComponent.class );
        JournalComponent child2 = mock ( JournalComponent.class );

        composite.add ( child1 );
        composite.add ( child2 );

        composite.print ();

        verify ( child1 ).print ();
        verify ( child2 ).print ();
    }

    @Test
    public void testAddNull() {
        assertThrows ( NullPointerException.class, () -> {
            composite.add ( null );
        } );
    }

}
