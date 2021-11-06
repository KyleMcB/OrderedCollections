import orderedCollection.tree.Tree
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals
import kotlin.test.assertNull

interface EmptyTreeTests<K : Comparable<K>, V> {
    val tree: Tree<K, V>
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

class TestEmptyTree : EmptyTreeTests<Int, String> {
    override val exampleValue: Pair<Int, String>
        get() = Pair(1, "hello")
    override val tree: Tree<Int, String>
        get() = Tree<Int, String>()
}