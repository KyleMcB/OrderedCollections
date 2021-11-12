/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package orderedCollection.map

import orderedCollection.MutableOrderedCollection
import orderedCollection.OrderedCollection


interface OrderedMap<K, V> : OrderedCollection<Pair<K, V>> {
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

interface MutableOrderedMap<K, V> : OrderedMap<K, V>,
    MutableOrderedCollection<Pair<K, V>>
