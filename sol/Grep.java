package sol;

import src.IGrep;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Class for finding the line numbers where a given word appears in a file.
 */
public class Grep implements IGrep {

    private String file;
    private HashMap<String, HashSet<Integer>> data;


    /**
     * Constructor for grep
     *
     * @param filename the name of the file
     */
    public Grep(String filename) {
        this.file = filename;
        HashMap<String, HashSet<Integer>> data = new HashMap<String, HashSet<Integer>>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line = null;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    if (!data.containsKey(word)) {
                        data.put(word, new HashSet<>());
                        data.get(word).add(lineNumber);
                    } else if (data.containsKey(word)) {
                        data.get(word).add(lineNumber);
                    }
                }
                lineNumber++;
            }
            reader.close();
            this.data = data;
        } catch (FileNotFoundException e) {
            System.err.println("Error: File " + filename + " not found.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Error: Could not read from file " + filename);
            System.exit(1);
        }
    }


    /**
     * The lookup function
     *
     * @param word - the word to look up
     * @return The set of all line numbers where the word appears
     */
    @Override
    public Set<Integer> lookup(String word) {
        Set<Integer> result = this.data.get(word);
        if (result == null) {
            result = Collections.emptySet();
        }
        return result;
    }

    /**
     * This is the main method. It takes in arguments (i.e. a file name and a word(s))
     * and calls your implementation of Grep.
     *
     * @param args - file name and word(s) you are looking up
     */
    public static void main(String[] args) {
        Grep grep = new Grep(args[0]);
        for (int i = 1; i < args.length; i++) {
            if (grep.lookup(args[i]) != null) {
                System.out.println(grep.lookup(args[i]).toString() + " was found on " + args[0]);
            } else {
                System.out.println("Did not find " + grep.lookup(args[i]).toString() + " in " + args[0]);
            }
        }
    }
}





