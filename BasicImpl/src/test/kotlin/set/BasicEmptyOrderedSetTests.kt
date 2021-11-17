/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package set

import orderedCollection.set.OrderedSet
import testsuite.set.EmptySetTests

class BasicEmptyOrderedSetTests : EmptySetTests<Int> {
    override val set: OrderedSet<Int> = BasicOrderedSet<Int>(naturalOrder())
    override val exampleValue = 1
}