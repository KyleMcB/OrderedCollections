import orderedCollection.map.MutableOrderedMap

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertIterableEquals
import org.junit.jupiter.api.Assertions.assertNull
import kotlin.test.assertEquals

interface MutableMapTests<K : Comparable<K>, V> {
    val tree: MutableOrderedMap<K, V>
    val valueGenerator: Sequence<Pair<K, V>>


    @Test
    fun size1() {
        tree.add(valueGenerator.first())
        assertEquals(1, tree.size)
    }

    @Test
    fun size5() {
        valueGenerator.take(5).toList().forEach { tree.add(it) }
        assertEquals(5, tree.size)
    }

    @Test
    fun inOrder() {
        val list = valueGenerator.take(100).toList()
        list.shuffled().forEach {
            tree.add(it)
        }
        assertIterableEquals(list.sortedBy { it.first }, tree)
    }

    @Test
    fun clear() {
        val list = valueGenerator.take(100).toList()
        list.forEach {
            tree.add(it)
        }
        tree.clear()
        assertIterableEquals(emptyList<Pair<K, V>>(), tree)
    }

    @Test
    fun at() {
        val list = valueGenerator.take(100).toList()
        list.forEach { tree.add(it) }
        val value = list.random()
        assertEquals(value.second, tree.at(value.first))
    }

    @Test
    fun atFail() {
        val list = valueGenerator.take(100).toMutableList()
        val value = list.random()
        list.remove(value)
        list.forEach { tree.add(it) }
        assertThrows<NoSuchElementException> { tree.at(value.first) }
    }

    @Test
    fun atOrNull() {
        val list = valueGenerator.take(100).toList()
        list.forEach { tree.add(it) }
        val value = list.random()
        assertEquals(value.second, tree.atOrNull(value.first))
    }

    @Test
    fun atOrNullFail() {
        val list = valueGenerator.take(100).toMutableList()
        val value = list.random()
        list.remove(value)
        list.forEach { tree.add(it) }
        assertNull(tree.atOrNull(value.first))
    }

    @RepeatedTest(3)
    fun headSet() {
        val list = valueGenerator.take(100).toMutableList()
        list.forEach { tree.add(it) }
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
        list.forEach { tree.add(it) }
        val upperList = list.subList(nextIndex, list.lastIndex + 1).sortedBy { it.first }
        assertIterableEquals(upperList, tree.headSet(value.first))
    }

    @RepeatedTest(3)
    fun tailSet() {
        val list = valueGenerator.take(100).toMutableList()
        list.forEach { tree.add(it) }
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
        list.forEach { tree.add(it) }
        val lowerList = list.subList(0, nextIndex).sortedBy { it.first }
        assertIterableEquals(lowerList, tree.tailSet(value.first))
    }

}

