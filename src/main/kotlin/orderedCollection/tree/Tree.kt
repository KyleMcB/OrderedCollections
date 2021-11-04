/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package orderedCollection.tree

import orderedCollection.OrderedCollection

interface Tree<K : Comparable<K>, V> : OrderedCollection<Pair<K, V>> {
    fun insert(pair: Pair<K, V>)
    fun at(key: K): V
    fun removeAt(key: K): Boolean
    fun atOrNull(key: K): V?
    fun headSet(key: K): Iterable<Pair<K, V>>
    fun tailSet(key: K): Iterable<Pair<K, V>>

    val insertMode: InsertMode
    enum class InsertMode {
        REPLACE,
        IGNORE
    }
}

fun <K : Comparable<K>, V> Tree(): Tree<K, V> = BSTree<K, V>()
