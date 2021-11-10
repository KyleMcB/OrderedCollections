/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package orderedCollection.map

import orderedCollection.MutableOrderedCollection
import orderedCollection.OrderedCollection

/**
 * This class is a default comparator for pairs to satisfy the Comparator for OrderedCollection.
 *
 * @param K the key type of the map
 * @param V The value type the map
 */
class MapPairComparator<K : Comparable<K>, V> : Comparator<Pair<K, V>> {
    override fun compare(o1: Pair<K, V>, o2: Pair<K, V>) = o1.first.compareTo(o2.first)
}

/**
 * A map of K -> V that maintains order based on natural ordering of K
 *
 * @param K the key type used to determine order of elements
 * @param V the value to be stored in the map
 */
interface OrderedMap<K : Comparable<K>, V> : OrderedCollection<Pair<K, V>, MapPairComparator<K, V>> {
    fun at(key: K): V

    fun atOrNull(key: K): V?
    fun headSet(key: K): Iterable<Pair<K, V>>
    fun tailSet(key: K): Iterable<Pair<K, V>>
    fun subList(start: K, end: K): Iterable<Pair<K, V>>
    val duplicateKeyMode: InsertMode

    //    Enum class to declare what an OrderedMap should do when inserter at a key that already exists
    enum class InsertMode {
        //    delete the old value and insert the new one at the key
        REPLACE,

        //    ignore the new value and keep the value already in the map
        IGNORE
    }
}

interface MutableOrderedMap<K : Comparable<K>, V> : OrderedMap<K, V>,
    MutableOrderedCollection<Pair<K, V>, MapPairComparator<K, V>>
