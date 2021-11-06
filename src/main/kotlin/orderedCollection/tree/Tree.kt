/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package orderedCollection.tree

import orderedCollection.OrderedCollection


class TreeComparator<K : Comparable<K>, V> : java.util.Comparator<Pair<K, V>> {
    override fun compare(o1: Pair<K, V>?, o2: Pair<K, V>?): Int {
        return when {
            o1 == null && o2 == null -> 0
            o1 == null && o2 != null -> -1
            o1 != null && o2 == null -> 1
            else -> o1!!.first.compareTo(o2!!.first)
        }
    }
}

interface Tree<K : Comparable<K>, V> : OrderedCollection<Pair<K, V>, TreeComparator<K, V>> {
    fun at(key: K): V

    fun atOrNull(key: K): V?
    fun headSet(key: K): Iterable<Pair<K, V>>
    fun tailSet(key: K): Iterable<Pair<K, V>>
    fun subList(start: K, end: K): Iterable<Pair<K, V>>
    val insertMode: InsertMode

    enum class InsertMode {
        REPLACE,
        IGNORE
    }
}

interface MutableTree<K : Comparable<K>, V> : Tree<K, V> {
    fun removeAt(key: K): Boolean
    fun insert(pair: Pair<K, V>)
    fun clear()
}

fun <K : Comparable<K>, V> Tree(): Tree<K, V> = BSTree<K, V>()
fun <K : Comparable<K>, V> MutableTree(): Tree<K, V> = BSTree<K, V>()
