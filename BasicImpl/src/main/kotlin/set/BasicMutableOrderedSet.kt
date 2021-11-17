/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package set

import orderedCollection.set.MutableOrderedSet

class BasicMutableOrderedSet<E>(override val comparator: Comparator<E>, val set: MutableList<E> = mutableListOf()) :
    MutableOrderedSet<E> {
    override fun add(element: E): Boolean {
        val index = set.binarySearch(element, comparator)
        return if (index < 0) {
            //not in set
            val positiveIndex = -(index + 1)
            set.add(positiveIndex, element)
            true
        } else {
            //in set
            false
        }
    }

    override val size = set.size

    override fun contains(element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): MutableIterator<E> = set.listIterator()

    override fun addAll(elements: Collection<E>): Boolean {
        val before = size
        for (element in elements) {
            add(element)
        }
        val after = size
        return before < after
    }

    override fun clear() {
        TODO("Not yet implemented")
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
}