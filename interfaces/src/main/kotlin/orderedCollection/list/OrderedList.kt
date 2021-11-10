package orderedCollection.list

import orderedCollection.OrderedCollection

class DefaultListComparator<E : Comparable<E>> : Comparator<E> {
    override fun compare(o1: E, o2: E) = o1.compareTo(o2)
}

interface OrderedList<E : Comparable<E>> : OrderedCollection<E, DefaultListComparator<E>>, List<E>

interface MutableOrderedList<E : Comparable<E>> : OrderedCollection<E, DefaultListComparator<E>>, MutableCollection<E>,
    List<E>