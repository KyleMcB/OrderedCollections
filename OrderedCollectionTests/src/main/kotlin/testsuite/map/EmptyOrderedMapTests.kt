package testsuite.map

import orderedCollection.map.OrderedMap
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.*

interface EmptyOrderedMapTests<K, V> {
    val map: OrderedMap<K, V>
    val exampleValue: Pair<K, V>

    @Test
    fun size() {
        assertEquals(0, map.size)
    }

    @Test
    fun isEmpty() {
        assertTrue(map.isEmpty())
    }

    @Test
    fun iterator() {
        assertContentEquals(emptyList(), map)
    }

    @Test
    fun containsAll() {
        assertFalse { map.containsAll(listOf(exampleValue)) }
    }

    @Test
    fun contains() {
        assertFalse { map.contains(exampleValue) }
    }

    @Test
    fun subList() {
        assertContentEquals(emptyList(), map.subList(exampleValue.first, exampleValue.first))
    }

    @Test
    fun tailSet() {
        assertContentEquals(emptyList(), map.tailSet(exampleValue.first))
    }

    @Test
    fun headSet() {
        assertContentEquals(emptyList(), map.headSet(exampleValue.first))
    }

    @Test
    fun atOrNull() {
        assertNull(map.atOrNull(exampleValue.first))
    }

    @Test
    fun at() {
        assertThrows<NoSuchElementException> { map.at(exampleValue.first) }
    }
}