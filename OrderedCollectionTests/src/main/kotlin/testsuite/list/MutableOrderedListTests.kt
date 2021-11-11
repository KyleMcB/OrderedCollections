package testsuite.list

import orderedCollection.list.MutableOrderedList
import org.junit.jupiter.api.Test

interface MutableOrderedListTests<E : Comparable<E>> {
    val list: MutableOrderedList<E>
    val values: Sequence<E>

    @Test
    fun sizeIsOneAfterAdd() {
        list.add(values.take(1).first())
        assert(list.size == 1)
    }
}