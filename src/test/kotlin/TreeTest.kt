/*
 * Copyright (c) 2021. Kyle McBurnett
 */


import orderedCollection.tree.BSTree
import orderedCollection.tree.MutableTree
import orderedCollection.tree.Tree
import kotlin.test.*

const val badData = "data retrieved did not match inserted"


class EmptyTreeTests() {
    val tree: MutableTree<Int, String> = BSTree<Int, String>()

    @Test
    fun addData() {
        tree.insert(1 to "hi")
    }
    @Test
    fun atNoElement() {
        assertFails { tree.at(1) }
    }
    @Test
    fun atOrNullNoElement() {
        assertNull(tree.atOrNull(1))
    }

    @Test
    fun addAndGetOneDatum() {
        tree.insert(1 to "hi")
        val value = tree.at(1)
        assertEquals("hi", value, badData)
    }

    @Test
    fun addAndGetTwoData() {
        tree.insert(1 to "hi")
        tree.insert(2 to "other")
        val hi = tree.at(1)
        val other = tree.at(2)
        assertEquals("hi", hi, badData)
        assertEquals("other", other, badData)
    }
    @Test
    fun replace() {
        tree.insert(1 to "hi")
        tree.insert(2 to "hi")
        tree.insert(1 to "hello")
        assertEquals("hello", tree.at(1), "element should be replaced")
        assertEquals("hi", tree.at(2), "we lost the child element when replacing")
    }
    @Test
    fun ingore() {
        val tree = BSTree<Int, String>(insertMode = Tree.InsertMode.IGNORE)
        tree.insert(1 to "hi")
        tree.insert(2 to "hi")
        tree.insert(1 to "hello")
        assertEquals("hi", tree.at(1), "new element should be ignored")
        assertEquals("hi", tree.at(2), "we lost the child element when replacing")
    }

    @Test
    fun addOutOfOrderIterateInOrder() {
        tree.insert(5 to "hi")
        tree.insert(1 to "other")
        tree.insert(2 to "2")
        var result = ""
        for (item in tree) {
            result += item.second
        }
        assertEquals("other2hi", result, "inorder iterator failed")
    }
    @Test
    fun sortedOrder() {
        // POUT
        // lets add a lot of elements and check they are sorted
        val set = mutableSetOf<Int>()
        repeat(1000) { set.add((0..9999).random()) }
        set.forEach { tree.insert(it to it.toString()) }
        val list = mutableListOf<Int>()
        for (item in tree) {
            list.add(item.first)
        }
        assertEquals(set.sorted(), list, "elements from the tree should be in sorted order")
    }
    @Test
    fun attempRemoveNonElement() {
        tree.insert(1 to "1")
        val res = tree.removeAt(2)
        assertEquals(false, res)
    }
    @Test
    fun removeOneElement() {
        tree.insert(1 to "1")
        tree.removeAt(1)
        assertEquals(0, tree.size)
        assertNull(tree.atOrNull(1))
    }
    @Test
    fun removeWithOneChild() {
        tree.insert(1 to "1")
        tree.insert(2 to "2")
        tree.removeAt(1)
        assertEquals(1, tree.size)
        assertNull(tree.atOrNull(1))
    }
    @Test
    fun removeWithOneChild2() {
        tree.insert(2 to "2")
        tree.insert(1 to "1")
        tree.removeAt(2)
        assertEquals(1, tree.size)
        assertNull(tree.atOrNull(2))
    }
    @Test
    fun removeWithFullSubTrees() {
        tree.insert(4 to "4")
        tree.insert(2 to "2")
        tree.insert(6 to "6")
        tree.insert(1 to "1")
        tree.insert(3 to "3")
        tree.insert(5 to "5")
        tree.insert(7 to "7")
        tree.removeAt(4)
        assertEquals(6, tree.size)
        val list = listOf(1, 2, 3, 5, 6, 7)
        val listFromTree = tree.toList().map { it.first }
        assertEquals(list, listFromTree)
        tree.removeAt(2)
        val list2 = listOf(1, 3, 5, 6, 7)
        val listFromTree2 = tree.toList().map { it.first }
        assertEquals(list2, listFromTree2)
    }

    // get an iterable that is greater than or equal to a key
    @Test
    fun getAnIterableheadSet() {
        tree.insert(4 to "4")
        tree.insert(2 to "2")
        tree.insert(6 to "6")
        tree.insert(1 to "1")
        tree.insert(3 to "3")
        tree.insert(5 to "5")
        tree.insert(7 to "7")
        val list = listOf(4, 5, 6, 7)
        val upperHalf = tree.headSet(4).toList().map { it.first }
        assertContentEquals(list, upperHalf)
    }
    // iterator from headset of empty has nothing
    @Test
    fun getIteratorEmptyFails() {
        val iter = tree.headSet(5).iterator()
        assertEquals(false, iter.hasNext())
    }
    // if headset can not find a match then the closest one that is greater
    @Test
    fun getAnIterableheadSetWithoutMatch() {
        tree.insert(4 to "4")
        tree.insert(2 to "2")
        tree.insert(6 to "6")
        tree.insert(1 to "1")
        tree.insert(3 to "3")
        tree.insert(5 to "5")
        tree.insert(7 to "7")
        tree.removeAt(4)
        val list = listOf(5, 6, 7)
        val upperHalf = tree.headSet(4).toList().map { it.first }
        assertContentEquals(list, upperHalf)
    }

    @Test
    fun getTailSet() {
        val list = tree.tailSet(4).toList().map { it.first }
        assertTrue(list.isEmpty())
    }

    @Test
    fun getAnIterabletailSet() {
        tree.insert(4 to "4")
        tree.insert(2 to "2")
        tree.insert(6 to "6")
        tree.insert(1 to "1")
        tree.insert(3 to "3")
        tree.insert(5 to "5")
        tree.insert(7 to "7")
        val list = listOf(1, 2, 3, 4)
        val lowerHalf = tree.tailSet(4).toList().map { it.first }
        assertContentEquals(list, lowerHalf)
    }

    @Test
    fun getAnIterabletailSetNoMatch() {


        tree.insert(2 to "2")
        tree.insert(6 to "6")
        tree.insert(1 to "1")
        tree.insert(3 to "3")
        tree.insert(5 to "5")
        tree.insert(7 to "7")
        val list = listOf(1, 2, 3)
        val lowerHalf = tree.tailSet(4).toList().map { it.first }
        assertContentEquals(list, lowerHalf)
    }

    @Test
    fun getsubList() {
        val list = tree.subList(0, 1)
        assertContentEquals(list, emptyList())
    }

    @Test
    fun getAnIterablesubSet() {
        tree.insert(4 to "4")
        tree.insert(2 to "2")
        tree.insert(6 to "6")
        tree.insert(1 to "1")
        tree.insert(3 to "3")
        tree.insert(5 to "5")
        tree.insert(7 to "7")
        val list = listOf(2, 3, 4)
        val range = tree.subList(2, 4).toList().map { it.first }
        assertContentEquals(list, range)
    }

    @Test
    fun getAnIterablesubSetNoMatchStart() {
        tree.insert(4 to "4")

        tree.insert(6 to "6")
        tree.insert(1 to "1")
        tree.insert(3 to "3")
        tree.insert(5 to "5")
        tree.insert(7 to "7")
        val list = listOf(3, 4)
        val range = tree.subList(2, 4).toList().map { it.first }
        assertContentEquals(list, range)
    }

    @Test
    fun getAnIterablesubSetNoMatchEnd() {

        tree.insert(2 to "2")
        tree.insert(6 to "6")
        tree.insert(1 to "1")
        tree.insert(3 to "3")
        tree.insert(5 to "5")
        tree.insert(7 to "7")
        val list = listOf(2, 3)
        val range = tree.subList(2, 4).toList().map { it.first }
        assertContentEquals(list, range)
    }

    @Test
    fun clear() {
        tree.clear()
        assertTrue(tree.size == 0)
    }
}
