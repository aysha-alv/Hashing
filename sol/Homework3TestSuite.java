package sol;

import org.junit.Assert;
import src.IDictionary;
import src.KeyAlreadyExistsException;
import src.KeyNotFoundException;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertNotEquals;


/**
 * Test suite for hash tables
 */
public class Homework3TestSuite {

    /**
     * This example is here to show you how to input the filenames for your Grep
     * tests
     */

    public static void testGrepExample() throws FileNotFoundException {
        Grep grep = new Grep("grep-test-files/ozymandias.txt");
        Set<Integer> result = grep.lookup("traveller");
    }

    /**
     * This test is here to make sure the stencil is set up correctly
     * Feel free to delete it!
     */

    @Test
    public void testGrep() throws FileNotFoundException {
        Grep grep = new Grep("/Users/ayshaallahverdi/Desktop/CS200/hw03-hashing-aysha-alv/grep-test-files/ozymandias.txt");
        Set<Integer> result = grep.lookup("colossal");
        System.out.println(result);
    }

    @Test
    public void testGrepExample2() {
        Grep grep = new Grep("grep-test-files/ozymandias.txt");
        Set<Integer> result = grep.lookup("tra eller");
        System.out.println(result);
        HashSet<Integer> i = new HashSet<Integer>();
        i.add(2);
        i.add(5);
        i.add(10);
        Assert.assertEquals(i, grep.lookup("of"));
        i.remove(5);
        i.remove(2);
        Assert.assertEquals(i, grep.lookup("King"));
        i.remove(10);
        Assert.assertEquals(i, grep.lookup("boba"));



    }
    @Test
    public void testEmptyHashTableLookup() {
        Chaining<String, Integer> hashtable = new Chaining<>(5);

        try {
            hashtable.lookup("key");
            fail("Expected KeyNotFoundException not thrown.");
        } catch (KeyNotFoundException e) {
        }
    }

    @Test
    public void testInsert() throws Exception {
        IDictionary<Integer, String> hashtable = new Chaining<>(5);

        hashtable.insert(1, "Aysha");
        assertEquals("Aysha", hashtable.lookup(1));

        hashtable.insert(2, "Sana");
        hashtable.insert(3, "Salonee");
        hashtable.insert(4, "Will");
        assertEquals("Sana", hashtable.lookup(2));
        assertEquals("Salonee", hashtable.lookup(3));
        assertEquals("Will", hashtable.lookup(4));
        assertEquals("Aysha", hashtable.lookup(1));
    }

    @Test
    public void testEquals() throws KeyAlreadyExistsException, KeyNotFoundException {
        IDictionary<String, String> hashtable1 = new Chaining<>(5);
        IDictionary<String, String> hashtable2 = new Chaining<>(5);
        IDictionary<String, String> hashtable3 = new Chaining<>(5);
        IDictionary<String, String> hashtable4 = new Chaining<>(5);


        hashtable1.insert("A", "Aysha");

        hashtable2.insert("S", "Sana");

        hashtable3.insert("S", "Salonee");
        hashtable3.insert("W", "Will");

        hashtable4.insert("S", "Salonee");
        hashtable4.insert("W", "Will");

        Assert.assertFalse(hashtable1.equals(hashtable2));
        Assert.assertTrue(hashtable3.equals(hashtable4));

        Chaining chainy = new Chaining(2);
        chainy.insert("a", "a1");
        chainy.insert("b", "b1");
        Chaining chainy2 = new Chaining(2);
        chainy2.insert("b", "b1");
        chainy2.insert("c", "c1");
        chainy2.insert("a", "a1");
        Assert.assertFalse(chainy.equals(chainy2));
        chainy2.delete("c");
        Assert.assertTrue(chainy.equals(chainy2));

        }


    Chaining numbers;

    Chaining strings;

    @Test
    public void testChaining() throws KeyAlreadyExistsException, KeyNotFoundException {
        Chaining numbers = new Chaining(2);
        this.numbers = numbers;
        Chaining strings = new Chaining(2);
        this.strings = strings;
        this.strings.insert("aysha", "a");
        this.numbers.insert(1, 2);
        this.numbers.insert(3, 4);
        Assert.assertEquals(4, this.numbers.lookup(3));
        Assert.assertEquals("a", this.strings.lookup("aysha"));
        this.strings.update("aysha", "A");
        Assert.assertEquals("A", this.strings.lookup("aysha"));
    }

    @Test(expected = KeyNotFoundException.class)
    public void testChainingNotFound() throws KeyNotFoundException, KeyAlreadyExistsException {
        Chaining numbers = new Chaining(3);
        this.numbers = numbers;
        Chaining strings = new Chaining(2);
        this.strings = strings;
        this.numbers.lookup(0);
        this.strings.lookup(1);
        this.numbers.update(1, "b");
        this.strings.update(0, "a");
        this.numbers.insert(2, "c");
        this.strings.delete(2);
        this.numbers.update(2, "d");
        this.numbers.lookup(2);

    }
}

