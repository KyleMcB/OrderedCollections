/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package set

import orderedCollection.set.MutableOrderedSet

class BasicMutableOrderedSet<E>(override val comparator: Comparator<E>, val set: MutableList<E> = mutableListOf()) :
    MutableOrderedSet<E> {
    override fun add(element: E): Boolean {
        TODO("Not yet implemented")
    }

    override val size: Int
        get() = TODO("Not yet implemented")

    override fun contains(element: E): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): MutableIterator<E> {
        TODO("Not yet implemented")
    }

    override fun addAll(elements: Collection<E>): Boolean {
        TODO("Not yet implemented")
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