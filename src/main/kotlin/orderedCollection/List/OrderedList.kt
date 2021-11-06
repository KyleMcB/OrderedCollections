package orderedCollection.List

import orderedCollection.OrderedCollection

class ListComparator<E : Comparable<E>> : Comparator<E> {
    override fun compare(o1: E, o2: E): Int {
        return o1.compareTo(o2)
    }
}

class OrderedList<E : Comparable<E>>() : OrderedCollection<E, ListComparator<E>> {
    private val list = mutableListOf<E>()
    override val size: Int
        get() = list.size

    override fun contains(element: E) = list.contains(element)

    override fun containsAll(elements: Collection<E>) = list.containsAll(elements)

    override fun isEmpty() = list.isEmpty()

    override fun iterator() = list.iterator()
}