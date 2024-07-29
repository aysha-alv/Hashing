package sol;

import src.IDictionary;
import src.KeyAlreadyExistsException;
import src.KeyNotFoundException;

import java.util.LinkedList;

/**
 * A class that implements hash tables using chaining.
 */
public class Chaining<K, V> implements IDictionary<K, V> {

    /**
     * A class that represents key-value pairs.
     */
    private static class KVPair<K, V> {
        public K key;
        public V value;

        /**
         * @param key - key in the pair
         * @param value - value in the pair
         */
        public KVPair(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Constructor for hash table
     * @param size Size of the hash table
     */
    private LinkedList<KVPair<K, V>>[] data;

    /**
     * makes a chaining hashtable with specific size
     *
     * @param size - the size of the hashtable
     * @return a new hashtable
     */
    @SuppressWarnings("unchecked")
    public Chaining(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Invalid size.");
        }
        this.data = (LinkedList<KVPair<K, V>>[]) new LinkedList[size];
        for (int i = 0; i < size; i++) {
            this.data[i] = new LinkedList<KVPair<K, V>>();
        }
    }

    /**
     * does the compression/mapping
     *
     * @param key - the key to look up
     *
     * @return int of modulo for the key
     */
    private int hashFun(K key) {
        return Math.abs(key.hashCode() % this.data.length);
    }


    /**
     * Finds a key value pair in the hashtable.
     *
     * @param key - the key to look up
     *
     * @return the key-value pair found
     * @throws KeyNotFoundException if the key is not found
     */
    private KVPair<K, V> findKVPair(K key) throws KeyNotFoundException {
            int index = this.hashFun(key);
            LinkedList<KVPair<K, V>> list = this.data[index];
            for (KVPair<K, V> pair : list) {
                if (pair.key.equals(key)) {
                    return pair;
                }
            }
            throw new KeyNotFoundException();
        }

    /**
     * Finds a key in the hashtable.
     *
     * @param key - the key to look up
     *
     * @return the key-value pair found
     * @throws KeyNotFoundException if the key is not found
     */
    @Override
    public V lookup(K key) throws KeyNotFoundException {
        KVPair<K, V> pair = this.findKVPair(key);

        return pair.value;
    }


    /**
     * Updates value of kv pair
     *
     * @param key - the key to replace value for
     *
     * @return the updates value matching the key
     * @throws KeyNotFoundException if the key is not found
     */
    @Override
    public V update(K key, V value) throws KeyNotFoundException {
        KVPair<K, V> pair = this.findKVPair(key);

        V val = pair.value;
        pair.value = value;

        return val;
    }


    /**
     * Inserts a key into the hashmap
     *
     * @param key - the key to be inserted
     *
     * @throws KeyAlreadyExistsException if the key already has been inserted
     */
    @Override
    public void insert(K key, V value) throws KeyAlreadyExistsException {
        int index = this.hashFun(key);
        LinkedList<KVPair<K, V>> list = this.data[index];
        for (KVPair<K, V> pairs : list) {
            if (pairs.key.equals(key)) {
                throw new KeyAlreadyExistsException();
            }
        }
        list.add(new KVPair<K, V>(key, value));
    }

    /**
     * Deletes a key from the hashmap
     *
     * @param key - the key to be deleted
     * @return v the value corresponding to the deleted key
     *
     * @throws KeyNotFoundException if the key is ot found
     */
    @Override
    public V delete(K key) throws KeyNotFoundException {
            int index = this.hashFun(key);
            LinkedList<KVPair<K, V>> list = this.data[index];
            for (KVPair<K, V> pair : list) {
                if (pair.key.equals(key)) {
                    list.remove(pair);
                    return pair.value;
                }
            }
            throw new KeyNotFoundException();
        }

    /**
     * Calculates the size of the linked list kv pairs
     *
     * @return the size of the linked pair list
     *
     */
    public int size() {
        int count = 0;
        for (LinkedList<KVPair<K, V>> list : this.data) {
            count += list.size();
        }
        return count;
    }

    /**
     * Compares contents of 2 hashmaps to compare kv pairs
     *
     * @param ht - object to compare to
     *
     * @return boolean indicating if the two are equal
     */
    @Override
    public boolean equals(Object ht) {
            if (ht == null) {
                return false;
            }

            Chaining<K, V> other = (Chaining<K, V>) ht;
            if (this.size() != other.size()) {
                return false;
            }
            for (LinkedList<KVPair<K, V>> list : this.data) {
                for (KVPair<K, V> pair : list) {
                    try {
                        if (!pair.value.equals(other.lookup(pair.key))) {
                            return false;
                        }
                    } catch (KeyNotFoundException e) {
                        return false;
                    }
                }
            }

            return true;
        }

    /**
     * Turns kv chain to string
     *
     * @return the string value of the kv pair
     *
     */
    @Override
    public String toString() {
            String finalstring = "[";
            boolean more = true;
            for (LinkedList<KVPair<K, V>> list : this.data) {
                for (KVPair<K, V> pair : list) {
                    if (!more) {
                        finalstring += ", ";
                    } else {
                        more = false;
                    }
                    finalstring += pair.key.toString() + ": " + pair.value.toString();
                }
            }
            finalstring += "]";
            return finalstring;
    }
}
