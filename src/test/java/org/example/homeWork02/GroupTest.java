package org.example.homeWork02;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GroupTest {

    @Test
    void testGroupConstructor() {
        int testIndex = 5;
        Group group = new Group(testIndex);
        assertEquals(testIndex, group.index);
    }

    @Test
    void testGroupConstructorWithZeroIndex() {
        int testIndex = 0;
        Group group = new Group(testIndex);
        assertEquals(testIndex, group.index);
    }

}

