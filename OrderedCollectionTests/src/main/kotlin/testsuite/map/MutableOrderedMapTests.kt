package testsuite.map

import orderedCollection.map.MutableOrderedMap
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
        data.sortWith(map.comparator)
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
        data.sortWith(map.comparator)
        val lowerPart = data.subList(0, 5)
        val result = map.tailSet(lowerPart.last().first)
        assertContentEquals(lowerPart, result)
    }

    @Test
    fun tailSetNoMatch() {
        val data = values.take(10).toMutableList()
        data.sortWith(map.comparator)
        val removedItem = data[5]
        data.remove(removedItem)
        map.addAll(data)
        val lowerPart = data.subList(0, 4)
        val result = map.tailSet(removedItem.first)
        assertContentEquals(lowerPart, result)
    }

    @Test
    fun add() {
        val item = values.take(1).first()
        map.add(item)
        assertContentEquals(listOf(item), map)
    }

    @Test
    fun maintainSortAdd() {
        val data = values.take(100).shuffled().toList()
        data.forEach { map.add(it) }
        assertContentEquals(data.sortedBy { it.first }, map)
    }

    @Test
    fun addAll() {
        val data = values.take(100).shuffled().toList()
        map.addAll(data)
        assertContentEquals(data.sortedBy { it.first }, map)
    }

}