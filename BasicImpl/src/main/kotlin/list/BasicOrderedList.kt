package list

import orderedCollection.list.MutableOrderedList
import orderedCollection.list.OrderedList

class BasicOrderedList<E : Comparable<E>> : OrderedList<E>, AbstractList<E>(), MutableOrderedList<E> {
    private var _size: Int = 0
    override val size: Int
        get() {
            return _size
        }

    override fun add(element: E): Boolean {
        _size++
        return true
    }

    override fun addAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        _size = 0
    }

    override fun remove(element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun retainAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun contains(element: E): Boolean {
        return false
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return false
    }

    override fun isEmpty(): Boolean {
        return _size == 0
    }

    override fun iterator(): MutableIterator<E> {
        return mutableListOf<E>().iterator()
    }

    override fun get(index: Int): E {
        throw IndexOutOfBoundsException()
    }

    override fun indexOf(element: E): Int {
        return -1
    }

    override fun lastIndexOf(element: E): Int {
        return -1
    }

    inner class _Iterator(var index: Int = 0) : ListIterator<E> {

        init {
            if (index !in (0..size)) throw IndexOutOfBoundsException()
        }

        override fun hasNext(): Boolean {
            if (index > size) throw IndexOutOfBoundsException()
            return false
        }

        override fun hasPrevious(): Boolean {
            return false
        }

        override fun next(): E {
            if (index < 0) throw IndexOutOfBoundsException()
            throw NoSuchElementException()
        }

        override fun nextIndex(): Int {
            return 0
        }

        override fun previous(): E {
            throw NoSuchElementException()
        }

        override fun previousIndex(): Int {
            return -1
        }
    }

    override fun listIterator(): ListIterator<E> {
        return _Iterator()
    }

    override fun listIterator(index: Int): ListIterator<E> {
        return _Iterator(index)
    }


}
