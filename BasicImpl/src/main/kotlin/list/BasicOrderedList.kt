package list

import orderedCollection.list.OrderedList

class BasicOrderedList<E : Comparable<E>> : OrderedList<E> {
    override val size: Int
        get() = 0

    override fun contains(element: E): Boolean {
        return false
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        return false
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
        return -1
    }

    override fun lastIndexOf(element: E): Int {
        return -1
    }

    inner class _Iterator(var index: Int = 0) : ListIterator<E> {
        override fun hasNext(): Boolean {
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

    override fun subList(fromIndex: Int, toIndex: Int): List<E> {
        TODO("Not yet implemented")
    }
}
