package testsuite.map

import orderedCollection.map.MutableOrderedMap
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

interface MutableOrderedMapTests<K, V> {
    val map: MutableOrderedMap<K, V>
    val values: Sequence<Pair<K, V>>

    @Test
    fun add() {
        val item = values.take(1).first()
        map.add(item)
        assertContentEquals(listOf(item), map)
    }
}