package map

import orderedCollection.map.MutableOrderedMap
import testsuite.map.MutableOrderedMapTests

class BasicMutableOrderedMapTests : MutableOrderedMapTests<Int, Int> {
    override val map: MutableOrderedMap<Int, Int> = BasicOrderedMap(compareBy { it.first })
    override val values: Sequence<Pair<Int, Int>> = generateSequence(seed = 1 to 1) { it.first + 1 to it.first + 1 }
}