package com.assignment1;

import de.tilman_neumann.util.Multiset;
import de.tilman_neumann.util.Multiset_HashMapImpl;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class Multiset_HashMapImplTest {

    @Test
    public void testDefaultConstructor() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        assertEquals(0, multiset.totalCount());
        assertTrue(multiset.isEmpty());
    }

    @Test
    public void testCollectionConstructor() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>(Arrays.asList("A", "B", "A"));
        assertEquals(3, multiset.totalCount());
        assertEquals(Integer.valueOf(2), multiset.get("A"));
        assertEquals(Integer.valueOf(1), multiset.get("B"));
    }

    @Test
    public void testArrayConstructor() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>(new String[]{"A", "B", "A"});
        assertEquals(3, multiset.totalCount());
        assertEquals(Integer.valueOf(2), multiset.get("A"));
    }

    @Test
    public void testCopyConstructor() {
        Multiset_HashMapImpl<String> original = new Multiset_HashMapImpl<>(new String[]{"A", "B", "A"});
        Multiset_HashMapImpl<String> copy = new Multiset_HashMapImpl<>(original);
        assertEquals(3, copy.totalCount());
        assertEquals(Integer.valueOf(2), copy.get("A"));
        assertEquals(original, copy);
    }

    @Test
    public void testAddSingleElement() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        int oldMult = multiset.add("A");
        assertEquals(0, oldMult);
        assertEquals(1, multiset.totalCount());
        assertEquals(Integer.valueOf(1), multiset.get("A"));

        oldMult = multiset.add("A");
        assertEquals(1, oldMult);
        assertEquals(2, multiset.totalCount());
        assertEquals(Integer.valueOf(2), multiset.get("A"));
    }

    @Test
    public void testAddElementWithMultiplicity() {
        Multiset_HashMapImpl<String> multiset = new Multiset_HashMapImpl<>();
        int oldMult = multiset.add("A", 3);
        assertEquals(0, oldMult);
        assertEquals(3, multiset.totalCount());
        assertEquals(Integer.valueOf(3), multiset.get("A"));

        oldMult = multiset.add("A", 2);
        assertEquals(3, oldMult);
        assertEquals(5, multiset.totalCount());
        assertEquals(Integer.valueOf(5), multiset.get("A"));
    }

    @Test
    public void testAddAllMultiset() {
        Multiset_HashMapImpl<String> ms1 = new Multiset_HashMapImpl<>(new String[]{"A", "A", "B"});
        Multiset_HashMapImpl<String> ms2 = new Multiset_HashMapImpl<>(new String[]{"A", "C"});
        ms1.addAll((Multiset<String>) ms2);
        assertEquals(5, ms1.totalCount());
        assertEquals(Integer.valueOf(3), ms1.get("A"));
        assertEquals(Integer.valueOf(1), ms1.get("B"));
        assertEquals(Integer.valueOf(1), ms1.get("C"));
    }

    @Test
    public void testRemoveSingleObject() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>(new String[]{"A", "A", "B"});
        Integer oldMult = ms.remove((Object) "A");
        assertEquals(Integer.valueOf(2), oldMult);
        assertEquals(Integer.valueOf(1), ms.get("A"));
        
        oldMult = ms.remove((Object) "B");
        assertEquals(Integer.valueOf(1), oldMult);
        assertNull(ms.get("B"));
    }

    @Test
    public void testRemoveElementWithMultiplicity() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>(new String[]{"A", "A", "A"});
        int oldMult = ms.remove("A", 2);
        assertEquals(3, oldMult);
        assertEquals(Integer.valueOf(1), ms.get("A"));

        oldMult = ms.remove("A", 5); // Removing more than existing leaves it removed
        assertEquals(1, oldMult);
        assertNull(ms.get("A"));
    }

    @Test
    public void testRemoveAll() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>(new String[]{"A", "A", "A", "B"});
        int oldMult = ms.removeAll("A");
        assertEquals(3, oldMult);
        assertNull(ms.get("A"));
        assertEquals(Integer.valueOf(1), ms.get("B"));
    }

    @Test
    public void testIntersect() {
        Multiset_HashMapImpl<String> ms1 = new Multiset_HashMapImpl<>(new String[]{"A", "A", "A", "B"});
        Multiset_HashMapImpl<String> ms2 = new Multiset_HashMapImpl<>(new String[]{"A", "A", "C"});
        
        @SuppressWarnings("unchecked")
        Multiset<String> intersection = (Multiset<String>) ms1.intersect(ms2);
        
        Multiset_HashMapImpl<String> expected = new Multiset_HashMapImpl<>(new String[]{"A", "A"});
        assertEquals(expected, intersection);
    }

    @Test
    public void testToList() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>(new String[]{"A", "A", "B"});
        List<String> list = ms.toList();
        assertEquals(3, list.size());
        assertTrue(list.containsAll(Arrays.asList("A", "A", "B")));
    }

    @Test
    public void testToString() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        assertEquals("{}", ms.toString());
        
        ms.add("A", 2);
        assertEquals("{A^2}", ms.toString());
        
        ms.add("B");
        String output = ms.toString();
        // Since order isn't guaranteed, we just verify properties
        assertTrue(output.contains("A^2"));
        assertTrue(output.contains("B"));
        assertTrue(output.startsWith("{") && output.endsWith("}"));
    }

    @Test
    public void testEquals() {
        Multiset_HashMapImpl<String> ms1 = new Multiset_HashMapImpl<>(new String[]{"A", "A", "B"});
        Multiset_HashMapImpl<String> ms2 = new Multiset_HashMapImpl<>(new String[]{"B", "A", "A"});
        Multiset_HashMapImpl<String> ms3 = new Multiset_HashMapImpl<>(new String[]{"A", "B"});
        
        assertTrue(ms1.equals(ms2));
        assertFalse(ms1.equals(ms3));
        assertFalse(ms1.equals(null));
        assertFalse(ms1.equals("Not a multiset"));
    }

    @Test(expected = IllegalStateException.class)
    public void testHashCodeThrowsException() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.hashCode();
    }
}
