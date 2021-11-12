package testsuite.list

import orderedCollection.list.MutableOrderedList
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

interface MutableOrderedListTests<E : Comparable<E>> {
    val list: MutableOrderedList<E>
    val values: Sequence<E>

    @Test
    fun sizeIsOneAfterAdd() {
        list.add(values.take(1).first())
        assertEquals(1, list.size)
    }

    @Test
    fun sizeIsTwoAfterAdds() {
        values.take(2).toList().forEach {
            list.add(it)
        }
        assertEquals(2, list.size)
    }

    @Test
    fun sizeIsZeroAfterClear() {
        list.add(values.first())
        list.clear()
        assertEquals(0, list.size)
    }

    @Test
    fun isEmptyFalseAfterAdd() {
        list.add(values.first())
        assertFalse { list.isEmpty() }
    }

    @Test
    fun getEmpty() {
        assertThrows<IndexOutOfBoundsException> {
            list[0]
        }
    }

    @Test
    fun getOneElement() {
        val value = values.first()
        list.add(value)
        val result = list[0]
        assertEquals(value, result)
    }

    @Test
    fun addTwoRemoveOne() {
        val (value1, value2) = values.take(2).toList()
        list.add(value1)
        list.add(value2)
        list.remove(value1)
        assertEquals(value2, list[0])
    }

    @Test
    fun containsOne() {
        val value = values.first()
        list.add(value)
        assertTrue(list.contains(value))
    }
}