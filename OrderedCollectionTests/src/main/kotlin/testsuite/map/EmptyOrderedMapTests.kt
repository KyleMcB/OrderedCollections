package testsuite.map

import orderedCollection.map.OrderedMap
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

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
}