package list

import orderedCollection.list.MutableOrderedList
import orderedCollection.list.OrderedList
import testsuite.list.MutableOrderedListTests

class MutableBasicListTests : MutableOrderedListTests<Int> {
    override val list: MutableOrderedList<Int>
        get() = BasicOrderedList<Int>()
    override val values: Sequence<Int>
        get() = generateSequence() { (0..9999).random() }
}