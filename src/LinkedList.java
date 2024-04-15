import java.util.Optional;

public class LinkedList<K, V> {
    private K key;
    private V value;

    private LinkedList<K,V> next;
    private LinkedList<K,V> prev;

    /**
     * Create a new Element which links to itself
     * @param key The Key of the element
     * @param value The value of the element
     */
    public LinkedList(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = this;
        this.prev = this;
    }

    /**
     * Create a new List with a head element
     */
    public LinkedList() {
        this(null, null);
    }

    /**
     * Find thr first element with the given key.
     * May only be called on the head.
     * @param key The Key to find
     * @return An optional with the element if found.
     */
    public Optional<LinkedList<K,V>> find(K key) {
        if (this.key != null) throw new IllegalStateException("Key is not null find can only be called on the head");

        LinkedList<K,V> temp = this.next;
        while (temp.key != null) {
            if (temp.key.equals(key)) return Optional.of(temp);
            temp = temp.next;
            StepCounter.step();
        }

        return Optional.empty();
    }

    /**
     * Returns the value of the element. May not be called on the head.
     * @return The value object
     */
    public V value() {
        if (this.key == null) throw new IllegalStateException("Key is null head does not have a value");
        return this.value;
    }

    /**
     * Sets the value of the element. May not be called on the head.
     * @param value The new value
     */
    public void setValue(V value) {
        if (this.key == null) throw new IllegalStateException("Key is null head does not have a value");
        this.value = value;
    }

    /**
     * Removes the element from the list.
     * @return Just returns self
     */
    public LinkedList<K,V> delete() {
        this.prev.next = this.next;
        this.next.prev = this.prev;
        StepCounter.step();
        return this;
    }

    /**
     * Adds a new element behind this element.
     * @param key Te key of the new Element
     * @param value The value of the new Element
     */
    public void insert(K key, V value) {
        LinkedList<K,V> newNode = new LinkedList<>(key, value);
        newNode.next = this.next;
        this.next.prev = newNode;
        this.next = newNode;
        newNode.prev = this;
        StepCounter.step();
    }

    /**
     *
     * @return Returns the next element in the list Optional is empty if the next element is head.
     */
    public Optional<LinkedList<K,V>> next() {
        if (this.next.key == null) return Optional.empty();

        return Optional.of(this.next);
    }

    /**
     * Inserts an element at the tail of the list. Must be called on the head.
     * @param key The key of the new element
     * @param value The value of the new element
     */
    public void add(K key, V value) {
        if (this.key != null) throw new IllegalStateException("Key is not null add can only be called on the head");
        this.prev.insert(key, value);
    }
}
