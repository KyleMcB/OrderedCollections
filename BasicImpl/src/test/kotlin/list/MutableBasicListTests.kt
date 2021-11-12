package list

import orderedCollection.list.MutableOrderedList
import orderedCollection.list.OrderedList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import testsuite.list.MutableOrderedListTests

class MutableBasicListTests : MutableOrderedListTests<Int> {
    override val list: MutableOrderedList<Int> = BasicOrderedList<Int>()
    override val values: Sequence<Int> = generateSequence() { (0..9999).random() }

    @Test
    fun constructorArgSorted() {
        val data = values.take(100).shuffled().toList()
        val newList = BasicOrderedList<Int>(data.toMutableList())
        Assertions.assertIterableEquals(data.sorted(), newList)
    }
}