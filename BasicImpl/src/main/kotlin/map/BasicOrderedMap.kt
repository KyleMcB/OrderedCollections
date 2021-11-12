package map

import orderedCollection.map.OrderedMap

class BasicOrderedMap<K, V>(override val comparator: Comparator<Pair<K, V>>) : OrderedMap<K, V> {
    private val list: MutableList<Pair<K, V>> = mutableListOf()
    override fun at(key: K): V {
        TODO("Not yet implemented")
    }

    override fun atOrNull(key: K): V? {
        TODO("Not yet implemented")
    }

    override fun headSet(key: K): Iterable<Pair<K, V>> {
        TODO("Not yet implemented")
    }

    override fun tailSet(key: K): Iterable<Pair<K, V>> {
        TODO("Not yet implemented")
    }

    override fun subList(start: K, end: K): Iterable<Pair<K, V>> {
        TODO("Not yet implemented")
    }

    override val duplicateKeyMode: OrderedMap.InsertMode
        get() = TODO("Not yet implemented")
    override val size: Int = 0

    override fun contains(element: Pair<K, V>): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsAll(elements: Collection<Pair<K, V>>): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun iterator(): Iterator<Pair<K, V>> {
        TODO("Not yet implemented")
    }
}