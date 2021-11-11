package list

import orderedCollection.list.OrderedList

class BasicOrderedList<E : Comparable<E>> : OrderedList<E> {
    override val size: Int
        get() = 0

    override fun contains(element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        return true
    }

    override fun iterator(): Iterator<E> {
        return emptyList<E>().iterator()
    }

    override fun get(index: Int): E {
        throw IndexOutOfBoundsException()
    }

    override fun indexOf(element: E): Int {
        TODO("Not yet implemented")
    }

    override fun lastIndexOf(element: E): Int {
        TODO("Not yet implemented")
    }

    override fun listIterator(): ListIterator<E> {
        TODO("Not yet implemented")
    }

    override fun listIterator(index: Int): ListIterator<E> {
        TODO("Not yet implemented")
    }

    override fun subList(fromIndex: Int, toIndex: Int): List<E> {
        TODO("Not yet implemented")
    }
}
