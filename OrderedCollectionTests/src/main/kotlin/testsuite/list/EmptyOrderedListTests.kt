package testsuite.list

import orderedCollection.list.OrderedList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

interface EmptyOrderedListTests<E : Comparable<E>> {
    val list: OrderedList<E>
    val exampleValue: E

    @Test
    fun size() {
        assertTrue(list.size == 0)
    }

    @Test
    fun `is iterable`() {
        assertIterableEquals(emptyList<Int>(), list)
    }

    @Test
    fun `throws on get`() {
        assertThrows<IndexOutOfBoundsException> {
            list.get(0)
        }
    }

    @Test
    fun `is empty is true`() {
        assertTrue(list.isEmpty())
    }

    @Test
    fun `contains is false`() {
        assertFalse(
            list.contains(exampleValue)
        )
    }
}

