package map

import orderedCollection.map.OrderedMap
import testsuite.map.EmptyOrderedMapTests

class EmptyBasicMapTests : EmptyOrderedMapTests<Int, Int> {
    override val map: OrderedMap<Int, Int> = BasicOrderedMap(compareBy { it.first })
    override val exampleValue: Pair<Int, Int> = 1 to 1
}