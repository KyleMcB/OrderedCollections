package list

import testsuite.list.EmptyOrderedListTests
import orderedCollection.list.OrderedList
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class EmptyBasicListTests : EmptyOrderedListTests<Int> {
    override val list: OrderedList<Int> = BasicOrderedList<Int>()


}