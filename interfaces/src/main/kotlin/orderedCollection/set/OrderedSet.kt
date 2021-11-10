package orderedCollection.set

import orderedCollection.MutableOrderedCollection
import orderedCollection.OrderedCollection

class DefaultSetComparator<E : Comparable<E>> : Comparator<E> {
    override fun compare(o1: E, o2: E) = o1.compareTo(o2)
}

interface OrderedSet<E : Comparable<E>> : OrderedCollection<E, DefaultSetComparator<E>>, Set<E> {

}

interface MutableOrderedSet<E : Comparable<E>> : OrderedSet<E>, MutableOrderedCollection<E, DefaultSetComparator<E>>