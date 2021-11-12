package testsuite.map

import orderedCollection.map.MutableOrderedMap
import orderedCollection.map.OrderedMap
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull

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

    @Test
    fun headSetExactMatch() {
        val data = values.take(10).toList().sortedBy { it.first }
        map.addAll(data)
        val upperPart = data.subList(7, 10)
        val result = map.headSet(upperPart.first().first)
        assertContentEquals(upperPart.sortedBy { it.first }, result)
    }

    @Test
    fun headSetNoMatch() {
        val data = values.take(10).toMutableList()
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
        val data = values.take(10).toMutableList()
        map.addAll(data)
        data.sortBy { it.first }
        val lowerPart = data.subList(0, 5)
        val result = map.tailSet(lowerPart.last().first)
        assertContentEquals(lowerPart, result)
    }

    @Test
    fun tailSetNoMatch() {
        val data = values.take(10).toMutableList()
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
        val data = values.take(10).toMutableList()
        map.addAll(data)
        data.sortBy { it.first }
        val middlePart = data.subList(3, 7)
        val endElement = data[7]
        val result = map.subList(middlePart.first().first, endElement.first).toList()
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
        val (item1, item2) = values.take(2).toList()
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
        val data = values.take(10).distinct().toList()
        map.addAll(data)
        assertEquals(data.size, map.size)
    }

    @Test
    fun maintainSortAdd() {
        val data = values.take(100).shuffled().toList().distinct()
        data.forEach { map.add(it) }
        assertContentEquals(data.sortedBy { it.first }, map)
    }

    @Test
    fun addAll() {
        val data = values.take(100).shuffled().toList().distinct()
        map.addAll(data)
        assertContentEquals(data.sortedBy { it.first }, map)
    }

}