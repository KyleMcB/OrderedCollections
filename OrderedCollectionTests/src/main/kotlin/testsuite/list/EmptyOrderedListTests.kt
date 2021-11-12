@file:Suppress(
    "FunctionName", "FunctionName", "FunctionName", "FunctionName", "FunctionName", "FunctionName",
    "FunctionName", "FunctionName", "FunctionName", "FunctionName", "FunctionName", "FunctionName", "FunctionName",
    "FunctionName", "FunctionName", "FunctionName", "FunctionName"
)

package testsuite.list

import orderedCollection.list.OrderedList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

interface EmptyOrderedListTests<E> {
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
            list[0]
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

    @Test
    fun subList() {
        assertIterableEquals(emptyList<E>(), list.subList(0, 0))
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

    @Test
    fun `hasPrevious is false`() {
        assertFalse(list.listIterator().hasPrevious())
    }

    @Test
    fun `previous throws noSuchElement`() {
        assertThrows<NoSuchElementException> { list.listIterator().previous() }
    }

    @Test
    fun `previousIndex is -1`() {
        assertTrue(list.listIterator().previousIndex() == -1)
    }

    ////////////////////////////List iterator at index section///////////////////////////////
    @Test
    fun `list iterator at negative index throw IndexOutOfBounds`() {
        assertThrows<IndexOutOfBoundsException> { list.listIterator(-1) }
    }

    @Test
    fun listIteratorPassedEnd() {
        assertThrows<IndexOutOfBoundsException> { list.listIterator(1) }
    }

    @Test
    fun `list iterator0 hasNext is false`() {
        val it = list.listIterator(0)
        assertFalse(it.hasNext())
    }

    @Test
    fun `list iterator0 hasPrevious is false`() {
        assertFalse(list.listIterator(0).hasPrevious())
    }

    @Test
    fun `list iterator0 next throws NoSuchElement`() {
        assertThrows<NoSuchElementException> {
            list.listIterator(0).next()
        }
    }

    @Test
    fun iter0NextIndex() {
        assertTrue(list.listIterator(0).nextIndex() == 0)
    }

    @Test
    fun iter0Previous() {
        assertThrows<NoSuchElementException> { list.listIterator(0).previous() }
    }

    @Test
    fun iter0PreviousIndex() {
        assertTrue(list.listIterator(0).previousIndex() == -1)
    }

}

