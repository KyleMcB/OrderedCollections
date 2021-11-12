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
                val closeIndex = invertIndexFromSearch(index)
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
            else {
                val closeIndex = invertIndexFromSearch(index)
                return list.subList(0, closeIndex)
            }
        }
        return emptyList()
    }

    private fun borrowFirstValue(key: K) = key to list[0].second

    override fun subList(start: K, end: K): Iterable<Pair<K, V>> {
        if (isNotEmpty()) {
            val startPair = borrowFirstValue(start)
            val endPair = borrowFirstValue(end)
            val startIndex = positiveIndex(startPair)
            val endIndex = positiveIndex(endPair)
            return list.subList(startIndex, endIndex)
        }
        return emptyList()
    }

    private fun positiveIndex(element: Pair<K, V>): Int {
        val startIndex = list.binarySearch(element, comparator)
        return if (startIndex < 0) invertIndexFromSearch(startIndex) else startIndex
    }

    override val size: Int
        get() {
            return list.size
        }

    override fun contains(element: Pair<K, V>) = list.contains(element)

    override fun containsAll(elements: Collection<Pair<K, V>>) = false

    override fun isEmpty() = list.isEmpty()

    override fun iterator() = list.iterator()
    override fun add(element: Pair<K, V>): Boolean {
        val index = list.binarySearch(element, comparator)
        if (index < 0) {
            val actualIndex = invertIndexFromSearch(index)
            list.add(actualIndex, element)
        } else when (duplicateKeyMode) {
            OrderedMap.InsertMode.REPLACE -> list[index] = element
            OrderedMap.InsertMode.IGNORE -> return false
        }
        return true
    }

    private fun invertIndexFromSearch(index: Int) = -(index + 1)

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