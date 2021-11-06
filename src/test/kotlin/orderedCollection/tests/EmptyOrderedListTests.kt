package orderedCollection.tests

import orderedCollection.List.OrderedList
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Assumptions.assumeTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

interface EmptyOrderedListTests<E : Comparable<E>> {
    val emptyList: OrderedList<E>
    val exampleValue: E

    @Test
    fun setUp() {
        assertTrue(emptyList.size == 0)
    }

    @Test
    fun emptyIterator() {
        assertIterableEquals(emptyList, kotlin.collections.emptyList<E>())
    }
}

class OrderedListTestsImpl : EmptyOrderedListTests<Int> {
    override val emptyList = OrderedList<Int>()
    override val exampleValue = 1

}