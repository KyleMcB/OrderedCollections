package map

import orderedCollection.map.MutableOrderedMap
import orderedCollection.map.OrderedMap

class BasicOrderedMap<K, V>(
    override val comparator: Comparator<Pair<K, V>>,
    override val duplicateKeyMode: OrderedMap.InsertMode = OrderedMap.InsertMode.REPLACE
) : OrderedMap<K, V>, MutableOrderedMap<K, V> {
    private val list: MutableList<Pair<K, V>> = mutableListOf()
    override fun at(key: K): V {
        if (isNotEmpty()) {
            val item = borrowFirstValue(key)
            val index = list.binarySearch(item, comparator)
            if (index > -1)
                return list[index].second
        }
        throw NoSuchElementException()
    }

    override fun atOrNull(key: K): V? {
        return try {
            at(key)
        } catch (e: NoSuchElementException) {
            null
        }
    }

    override fun headSet(key: K): Iterable<Pair<K, V>> {
        if (isNotEmpty()) {
            val pair = borrowFirstValue(key)
            val index = list.binarySearch(pair, comparator)
            if (index > -1) return list.subList(index, list.size)
            else {
                val closeIndex = -(index + 1)
                return list.subList(closeIndex, list.size)
            }
        }
        return emptyList()
    }

    override fun tailSet(key: K): Iterable<Pair<K, V>> {
        if (isNotEmpty()) {
            val pair = borrowFirstValue(key)
            val index = list.binarySearch(pair, comparator)
            if (index > -1) return list.subList(0, index + 1)
        }
        return emptyList()
    }

    private fun borrowFirstValue(key: K) = key to list[0].second

    override fun subList(start: K, end: K) = emptyList<Pair<K, V>>()


    override val size: Int = 0

    override fun contains(element: Pair<K, V>) = false

    override fun containsAll(elements: Collection<Pair<K, V>>) = false

    override fun isEmpty() = list.isEmpty()

    override fun iterator() = list.iterator()
    override fun add(element: Pair<K, V>): Boolean {
        val index = list.binarySearch(element, comparator)
        if (index < 0) {
            val actualIndex = -(index + 1)
            list.add(actualIndex, element)
        } else when (duplicateKeyMode) {
            OrderedMap.InsertMode.REPLACE -> list[index] = element
            OrderedMap.InsertMode.IGNORE -> return false
        }
        return true
    }

    override fun addAll(elements: Collection<Pair<K, V>>) = list.addAll(elements).also { list.sortWith(comparator) }

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