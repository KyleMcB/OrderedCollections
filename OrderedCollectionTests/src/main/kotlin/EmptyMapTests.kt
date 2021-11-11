import orderedCollection.map.OrderedMap
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertContentEquals


interface EmptyMapTests<K : Comparable<K>, V> {
    val tree: OrderedMap<K, V>
    val exampleValue: Pair<K, V>

    @Test
    fun size() {
        assertEquals(0, tree.size)
    }

    @Test
    fun iterable() {
        assertContentEquals(emptyList(), tree)
    }

    @Test
    fun at() {
        assertThrows<NoSuchElementException> {
            tree.at(exampleValue.first)
        }
    }

    @Test
    fun atOrNull() {
        assertNull(tree.atOrNull(exampleValue.first))
    }

    @Test
    fun headSet() {
        assertContentEquals(emptyList(), tree.headSet(exampleValue.first))
    }

    @Test
    fun tailSet() {
        assertContentEquals(emptyList(), tree.tailSet(exampleValue.first))
    }

    @Test
    fun subList() {
        assertContentEquals(emptyList(), tree.subList(exampleValue.first, exampleValue.first))
    }

}

