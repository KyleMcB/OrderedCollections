/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package list

import orderedCollection.list.OrderedList
import testsuite.list.EmptyOrderedListTests

class EmptyBasicListTests : EmptyOrderedListTests<Int> {
    override val list: OrderedList<Int> = BasicOrderedList(naturalOrder())
    override val exampleValue: Int
        get() = 1
}