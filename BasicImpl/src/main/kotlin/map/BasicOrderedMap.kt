package map

import orderedCollection.map.MutableOrderedMap
import orderedCollection.map.OrderedMap

class BasicOrderedMap<K, V>(
    override val comparator: Comparator<Pair<K, V>>,
    override val duplicateKeyMode: OrderedMap.InsertMode = OrderedMap.InsertMode.REPLACE
) : OrderedMap<K, V>, MutableOrderedMap<K, V> {
    private val list: MutableList<Pair<K, V>> = mutableListOf()
    override fun at(key: K): V {
        throw NoSuchElementException()
    }

    override fun atOrNull(key: K): V? = null

    override fun headSet(key: K) = emptyList<Pair<K, V>>()

    override fun tailSet(key: K) = emptyList<Pair<K, V>>()

    override fun subList(start: K, end: K) = emptyList<Pair<K, V>>()


    override val size: Int = 0

    override fun contains(element: Pair<K, V>) = false

    override fun containsAll(elements: Collection<Pair<K, V>>) = false

    override fun isEmpty() = true

    override fun iterator() = list.iterator()
    override fun add(element: Pair<K, V>) = list.add(element)

    override fun addAll(elements: Collection<Pair<K, V>>): Boolean {
        TODO("Not yet implemented")
    }

    override fun clear() {
        TODO("Not yet implemented")
    }

    override fun remove(element: Pair<K, V>): Boolean {
        TODO("Not yet implemented")
    }

    override fun removeAll(elements: Collection<Pair<K, V>>): Boolean {
        TODO("Not yet implemented")
    }

    override fun retainAll(elements: Collection<Pair<K, V>>): Boolean {
        TODO("Not yet implemented")
    }
}