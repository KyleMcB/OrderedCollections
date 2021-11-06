import orderedCollection.tree.BSTree
import orderedCollection.tree.Tree
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assumptions.*
import org.junit.jupiter.api.BeforeEach

interface TreeTests<K : Comparable<K>, V> {
    val tree: Tree<K, V>

    @BeforeEach
    fun setUp() {
        assumeTrue(tree.size > 9)
    }

    @Test
    fun sorted() {
        val list = tree.toList().sortedBy { it.first }
        assertIterableEquals(list, tree)
    }

    @Test
    fun tailSet() {
        val list = tree.toList().take(5)
        assertIterableEquals(list, tree.tailSet(list.last().first))
    }

    @Test
    fun headSet() {
        val list = tree.toList().drop(4)
        assertIterableEquals(list, tree.headSet(list.first().first))
    }

    @Test
    fun subList() {
        val list = tree.toList().drop(2).dropLast(2)
        assertIterableEquals(list, tree.subList(list.first().first, list.last().first))
    }
}

class ImmutableBSTreeTests : TreeTests<Int, String> {
    override val tree: Tree<Int, String> = BSTree<Int, String>().apply {
        repeat(10) {
            insert(it to it.toString())
        }
    }
}

