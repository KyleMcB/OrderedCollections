import orderedCollection.tree.BSTree
import orderedCollection.tree.MutableTree
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Assertions.assertNull
import kotlin.test.assertEquals

interface MutableTreeTests<K : Comparable<K>, V> {
    val tree: MutableTree<K, V>
    val valueGenerator: Sequence<Pair<K, V>>

    @BeforeEach
    fun setUp() {
        //just incase the implementation has persistent storage
        tree.clear()
    }

    @Test
    fun size1() {
        tree.insert(valueGenerator.first())
        assertEquals(1, tree.size)
    }

    @Test
    fun size5() {
        valueGenerator.take(5).toList().forEach { tree.insert(it) }
        assertEquals(5, tree.size)
    }

    @Test
    fun inOrder() {
        val list = valueGenerator.take(100).toList()
        list.shuffled().forEach {
            tree.insert(it)
        }
        assertIterableEquals(list.sortedBy { it.first }, tree)
    }

    @Test
    fun clear() {
        val list = valueGenerator.take(100).toList()
        list.forEach {
            tree.insert(it)
        }
        tree.clear()
        assertIterableEquals(emptyList<Pair<K, V>>(), tree)
    }

    @Test
    fun at() {
        val list = valueGenerator.take(100).toList()
        list.forEach { tree.insert(it) }
        val value = list.random()
        assertEquals(value.second, tree.at(value.first))
    }

    @Test
    fun atFail() {
        val list = valueGenerator.take(100).toMutableList()
        val value = list.random()
        list.remove(value)
        list.forEach { tree.insert(it) }
        assertThrows<NoSuchElementException> { tree.at(value.first) }
    }

    @Test
    fun atOrNull() {
        val list = valueGenerator.take(100).toList()
        list.forEach { tree.insert(it) }
        val value = list.random()
        assertEquals(value.second, tree.atOrNull(value.first))
    }

    @Test
    fun atOrNullFail() {
        val list = valueGenerator.take(100).toMutableList()
        val value = list.random()
        list.remove(value)
        list.forEach { tree.insert(it) }
        assertNull(tree.atOrNull(value.first))
    }

    @RepeatedTest(3)
    fun headSet() {
        val list = valueGenerator.take(100).toMutableList()
        list.forEach { tree.insert(it) }
        val value = list.random()
        val upperList = list.subList(list.indexOf(value), list.lastIndex + 1).sortedBy { it.first }
        assertIterableEquals(upperList, tree.headSet(value.first))
    }

    @RepeatedTest(3)
    fun headSetWithoutMatch() {
        val list = valueGenerator.take(100).sortedBy { it.first }.toMutableList()
        val value = list.random()
        val nextIndex =
            list.indexOf(value) //after I remove this element from the list. This index will be the next element.
        list.remove(value)
        list.forEach { tree.insert(it) }
        val upperList = list.subList(nextIndex, list.lastIndex + 1).sortedBy { it.first }
        assertIterableEquals(upperList, tree.headSet(value.first))
    }

    @RepeatedTest(3)
    fun tailSet() {
        val list = valueGenerator.take(100).toMutableList()
        list.forEach { tree.insert(it) }
        val value = list.random()
        val lowerList = list.subList(0, list.indexOf(value) + 1).sortedBy { it.first }
        assertIterableEquals(lowerList, tree.tailSet(value.first))
    }

    @RepeatedTest(3)
    fun tailSetWithoutMatch() {
        val list = valueGenerator.take(100).sortedBy { it.first }.toMutableList()
        val value = list.random()
        val nextIndex =
            list.indexOf(value) //after I remove this element from the list. This index will be the next element.
        list.remove(value)
        list.forEach { tree.insert(it) }
        val lowerList = list.subList(0, nextIndex).sortedBy { it.first }
        assertIterableEquals(lowerList, tree.tailSet(value.first))
    }

}

class MutableTreeTest : MutableTreeTests<Int, String> {
    override val tree: MutableTree<Int, String> = BSTree()
    override val valueGenerator: Sequence<Pair<Int, String>> = generateSequence(seed = 0) { it + 1 }.map {
        it to it.toString()
    }
}