package testsuite.list

import orderedCollection.list.OrderedList
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

interface EmptyOrderedListTests<E : Comparable<E>> {
    val list: OrderedList<E>

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
}

