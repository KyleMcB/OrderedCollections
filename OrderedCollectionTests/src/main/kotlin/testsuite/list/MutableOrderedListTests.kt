package testsuite.list

import orderedCollection.list.MutableOrderedList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertContentEquals
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

    @Test
    fun iterator() {
        val (value1, value2) = values.take(2).toList()
        list.add(value1)
        list.add(value2)
        assertEquals(value1, list.first())
    }

    @Test
    fun emptyListAfterClear() {
        values.take(100).toList().forEach { list.add(it) }
        list.clear()
        assertIterableEquals(emptyList<E>(), list)
        assertContentEquals(emptyList<E>(), list)
    }

    @Test
    fun throwsAfterClearandGet() {
        values.take(5).toList().forEach { list.add(it) }
        list.clear()
        assertThrows<IndexOutOfBoundsException> { list[0] }
    }

    @Test
    fun addAll() {
        val data = values.take(100).toList()
        list.addAll(data)
        assertContentEquals(data, list)
    }

    @Test
    fun removeAllEmpty() {
        assertFalse { list.removeAll(emptyList()) }
    }

    @Test
    fun retainAllEmpty() {
        assertFalse { list.retainAll(emptyList()) }
    }

    @Test
    fun retianAllOne() {
        val data = values.take(100).toList()
        val result = data.toMutableList()
        result.retainAll(data.subList(20, 30))
        list.addAll(data)
        list.retainAll(data.subList(20, 30))
        assertIterableEquals(result, list)
    }

    @Test
    fun removeAllOne() {
        val data = values.take(10).toList()
        val result = data.toMutableList()
        result.removeAll(data.subList(2, 4))
        list.addAll(data)
        list.removeAll(data.subList(2, 4))
        assertIterableEquals(result, list)
    }

    @Disabled
    @Test
    fun sortedOrderAddAll() {
        val data = values.take(1000).shuffled().toList()
        list.addAll(data)
        assertIterableEquals(data.sorted(), list)
    }

    @Disabled
    @Test
    fun sortedOrderAdd() {
        val data = values.take(1000).shuffled().toList()
        data.forEach {
            list.add(it)
        }
        assertIterableEquals(data.sorted(), list)
    }
}