package testsuite.list

import orderedCollection.list.MutableOrderedList
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse

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
}