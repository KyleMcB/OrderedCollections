/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package set

import orderedCollection.set.MutableOrderedSet
import testsuite.set.OrderedSetTests

class BasicMutableOrderedSetTests : OrderedSetTests<Int> {
    override val set: MutableOrderedSet<Int> = BasicMutableOrderedSet<Int>(naturalOrder<Int>())
    override val infiniteSeq: Sequence<Int> = generateSequence { (0..9999).random() }
}