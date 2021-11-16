/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package map

import orderedCollection.map.MutableOrderedMap
import orderedCollection.map.OrderedMap
import testsuite.map.MutableOrderedMapTests

class BasicMutableOrderedMapTests : MutableOrderedMapTests<Int, Int> {
    override val map: MutableOrderedMap<Int, Int> = BasicOrderedMap(compareBy { it.first })
    override val values: Sequence<Pair<Int, Int>> = generateSequence() {
        val num = (0..9999).random()
        num to num
    }
}

class BasicMutableOrderedMapTestsIgnore : MutableOrderedMapTests<Int, Int> {
    override val map: MutableOrderedMap<Int, Int> =
        BasicOrderedMap(comparator = compareBy { it.first }, duplicateKeyMode = OrderedMap.InsertMode.IGNORE)
    override val values: Sequence<Pair<Int, Int>> = generateSequence() {
        val num = (0..9999).random()
        num to num
    }
}