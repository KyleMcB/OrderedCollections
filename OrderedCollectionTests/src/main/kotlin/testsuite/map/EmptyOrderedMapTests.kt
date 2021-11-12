package testsuite.map

import orderedCollection.map.OrderedMap
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

interface EmptyOrderedMapTests<K, V> {
    val map: OrderedMap<K, V>

    @Test
    fun size() {
        assertEquals(0, map.size)
    }
}