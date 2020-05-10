public interface IdentifiableCollection<E extends Identifiable> extends Iterable<E> {

    /**
     * Returns an element with the ID <code>id</code> that is stored in this <code>
     * IdentifiableCollection</code>. If the implementation of the interface is a set, the returned
     * element is unique, elsewise the method returns one of the contained elements with the given
     * ID.
     *
     * @param id the ID that shall be checked
     * @return the element with the ID <code>id</code> that is stored in this <code>
     *     IdentifiableCollection</code>, <code>null</code> if no element with this ID is stored.
     */
    E get(int id);
}
