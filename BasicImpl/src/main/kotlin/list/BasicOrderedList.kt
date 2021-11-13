/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package list

import orderedCollection.list.MutableOrderedList
import orderedCollection.list.OrderedList

fun <E : Comparable<E>> orderedListOf(): OrderedList<E> = BasicOrderedList(naturalOrder())
fun <E : Comparable<E>> orderedListOf(vararg es: E): OrderedList<E> =
    BasicOrderedList(comparator = naturalOrder(), list = es.toMutableList())

fun <E : Comparable<E>> mutableOrderedListOf(): MutableOrderedList<E> = BasicOrderedList(comparator = naturalOrder())
fun <E : Comparable<E>> mutableOrderedListOf(vararg items: E): MutableOrderedList<E> =
    BasicOrderedList(comparator = naturalOrder(), list = items.toMutableList())

fun <E : Comparable<E>> Collection<E>.toOrderedList(): OrderedList<E> =
    BasicOrderedList(comparator = naturalOrder(), list = this.toMutableList())

fun <E : Comparable<E>> Collection<E>.toMutableOrderedList(): MutableOrderedList<E> =
    BasicOrderedList(comparator = naturalOrder(), list = this.toMutableList())

/**
 * BasicOrderedList is a list the maintains sorted order using binarySearch, or just sorting after an addAll
 *
 * @param E Type held in the list
 * @property comparator comparison functor to keep the list sorted
 * @property list you can seed the list from another collection
 */
class BasicOrderedList<E>(
    override val comparator: Comparator<E>,
    private val list: MutableList<E> = mutableListOf()
) : OrderedList<E>, AbstractList<E>(), MutableOrderedList<E> {
    init {
        list.sortWith(comparator)
    }

    override val size: Int
        get() {
            return list.size
        }

    override fun add(element: E): Boolean {
        val potentialIndex = list.binarySearch(element = element, comparator = comparator)
        if (potentialIndex > 0) {
            list.add(potentialIndex, element)
        } else {
            val index = -(potentialIndex + 1)
            list.add(index, element)
        }
        return true
    }

    override fun addAll(elements: Collection<E>) = list.addAll(elements).also { list.sortWith(comparator) }

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
