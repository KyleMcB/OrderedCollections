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

    @Test
    fun `containsAll is false`() {
        assertFalse(list.containsAll(listOf(exampleValue)))
    }

    @Test
    fun `indexOf is -1 for emptyList`() {
        assertTrue(list.indexOf(exampleValue) == -1)
    }

    @Test
    fun `lastIndexOf is -1`() {
        assertTrue(list.lastIndexOf(exampleValue) == -1)
    }

    /////////////////////////////List iterator section///////////////////////////////
    @Test
    fun `list iterator hasNext is false`() {
        val it = list.listIterator()
        assertFalse(it.hasNext())
    }

    @Test
    fun `next throws NoSuchElement`() {
        assertThrows<NoSuchElementException> {
            list.listIterator().next()
        }

    }

    @Test
    fun `nextIndex is 0`() {
        assertTrue(list.listIterator().nextIndex() == 0)
    }
}

