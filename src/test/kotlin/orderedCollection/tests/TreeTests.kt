package orderedCollection.tests

import orderedCollection.tree.Tree
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assumptions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

interface TreeTests<K : Comparable<K>, V> {
    val tree: Tree<K, V>

    @BeforeEach
    fun setUp() {
        Assumptions.assumeTrue(tree.size > 9)
    }

    @Test
    fun sorted() {
        val list = tree.toList().sortedBy { it.first }
        Assertions.assertIterableEquals(list, tree)
    }

    @Test
    fun tailSet() {
        val list = tree.toList().take(5)
        Assertions.assertIterableEquals(list, tree.tailSet(list.last().first))
    }

    @Test
    fun headSet() {
        val list = tree.toList().drop(4)
        Assertions.assertIterableEquals(list, tree.headSet(list.first().first))
    }

    @Test
    fun subList() {
        val list = tree.toList().drop(2).dropLast(2)
        Assertions.assertIterableEquals(list, tree.subList(list.first().first, list.last().first))
    }
}