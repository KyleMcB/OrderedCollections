package list

import orderedCollection.list.MutableOrderedList
import orderedCollection.list.OrderedList

class BasicOrderedList<E : Comparable<E>>(private val list: MutableList<E> = mutableListOf<E>()) : OrderedList<E>,
    AbstractList<E>(), MutableOrderedList<E> {

    override val size: Int
        get() {
            return list.size
        }

    override fun add(element: E): Boolean {
        list.add(element)
        return true
    }

    override fun addAll(elements: Collection<E>) = list.addAll(elements)

    override fun clear() = list.clear()

    override fun remove(element: E): Boolean {
        return list.remove(element)
    }

    override fun removeAll(elements: Collection<E>) = list.removeAll(elements)

    override fun retainAll(elements: Collection<E>) = list.retainAll(elements)

    override fun contains(element: E) = list.contains(element)

    override fun containsAll(elements: Collection<E>) = list.containsAll(elements)

    override fun isEmpty() = list.isEmpty()

    override fun iterator() = list.iterator()

    override fun get(index: Int) = list[index]

    override fun indexOf(element: E) = list.indexOf(element)

    override fun lastIndexOf(element: E) = list.lastIndexOf(element)

    override fun listIterator() = list.listIterator()

    override fun listIterator(index: Int) = list.listIterator(index)


}
