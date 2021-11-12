package testsuite.map

import orderedCollection.map.MutableOrderedMap
import orderedCollection.map.OrderedMap
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.*

interface MutableOrderedMapTests<K : Comparable<K>, V> {
    val map: MutableOrderedMap<K, V>
    val values: Sequence<Pair<K, V>>

    @Test
    fun at() {
        val item = values.first()
        map.add(item)
        assertEquals(item.second, map.at(item.first))
    }

    @Test
    fun atfailure() {
        assertThrows<NoSuchElementException> { map.at(values.first().first) }
    }

    @Test
    fun atOrNullSuccess() {
        val item = values.first()
        map.add(item)
        assertEquals(item.second, map.atOrNull(item.first))
    }

    @Test
    fun atOrNullFailure() {
        assertNull(map.atOrNull(values.first().first))
    }

    val distinct: Sequence<Pair<K, V>>
        get() = values.distinct()

    @Test
    fun headSetExactMatch() {
        val data = distinct.take(10).toList().sortedBy { it.first }
        map.addAll(data)
        val upperPart = data.subList(7, 10)
        val result = map.headSet(upperPart.first().first)
        assertContentEquals(upperPart.sortedBy { it.first }, result)
    }

    @Test
    fun headSetNoMatch() {
        val data = distinct.take(10).toMutableList()
        data.sortBy { it.first }
        val item = data[7]
        data.remove(item)
        map.addAll(data)
        val upperPart = data.subList(7, data.size)
        val result = map.headSet(item.first)
        assertContentEquals(upperPart.sortedBy { it.first }, result)
    }

    @Test
    fun tailSetExactMatch() {
        val data = distinct.take(10).toMutableList()
        map.addAll(data)
        data.sortBy { it.first }
        val lowerPart = data.subList(0, 5)
        val result = map.tailSet(lowerPart.last().first)
        assertContentEquals(lowerPart, result)
    }

    @Test
    fun tailSetNoMatch() {
        val data = distinct.take(10).toMutableList()
        data.sortBy { it.first }
        val removedItem = data[4]
        data.remove(removedItem)
        map.addAll(data)
        val lowerPart = data.subList(0, 4)
        val result = map.tailSet(removedItem.first)
        assertContentEquals(lowerPart, result)
    }

    @Test
    fun sublistExactMatch() {
        val data = distinct.take(10).toMutableList()
        map.addAll(data)
        data.sortBy { it.first }
        val middlePart = data.subList(3, 7)
        val endElement = data[7]
        val result = map.subList(middlePart.first().first, endElement.first).toList()
        assertContentEquals(middlePart, result)
    }

    @Test
    fun sublistNoStartMatch() {
        val data = distinct.take(10).sortedBy { it.first }.toMutableList()
        val missingStartItem = data[4]
        data.remove(missingStartItem)
        map.addAll(data)
        val middlePart = data.subList(4, 7)
        val result = map.subList(missingStartItem.first, data[7].first)
        assertEquals(middlePart, result)
    }

    @Test
    fun sublistNoEndMatch() {
        val data = distinct.take(10).sortedBy { it.first }.toMutableList()
        val missingEnditem = data[8]
        data.remove(missingEnditem)
        map.addAll(data)
        val middlePart = data.subList(2, 8)
        val result = map.subList(data[2].first, missingEnditem.first)
        assertContentEquals(middlePart, result)
    }

    @Test
    fun sublistNoMatches() {
        val data = distinct.take(10).sortedBy { it.first }.toMutableList()
        val missingStart = data[3]
        val missingEnd = data[8]
        data.removeAll(listOf(missingEnd, missingStart))
        map.addAll(data)
        val middlePart = data.subList(3, 7)
        val result = map.subList(missingStart.first, missingEnd.first)
        assertContentEquals(middlePart, result)
    }

    @Test
    fun add() {
        val item = values.take(1).first()
        map.add(item)
        assertContentEquals(listOf(item), map)
    }

    @Test
    fun addNonUniqueReplace() {
        Assumptions.assumeTrue(map.duplicateKeyMode == OrderedMap.InsertMode.REPLACE)
        val (item1, item2) = distinct.take(2).toList()
        map.add(item1)
        val newItem = item1.first to item2.second
        map.add(newItem)
        assertEquals(newItem, map.first())

    }

    @Test
    fun size() {
        map.add(values.first())
        assertEquals(1, map.size)
    }

    @Test
    fun size2() {
        val data = distinct.take(10).toList()
        map.addAll(data)
        assertEquals(data.size, map.size)
    }

    @Test
    fun maintainSortAdd() {
        val data = distinct.take(100).shuffled().toList()
        data.forEach { map.add(it) }
        assertContentEquals(data.sortedBy { it.first }, map)
    }

    @Test
    fun addAll() {
        val data = distinct.take(100).shuffled().toList()
        map.addAll(data)
        assertContentEquals(data.sortedBy { it.first }, map)
    }

    @Test
    fun contains() {
        val item = distinct.first()
        map.add(item)
        assertTrue(map.contains(item))
    }
}