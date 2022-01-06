/*
 * Copyright (c) 2021-2022. Kyle McBurnett
 */

package testsuite.set

import orderedCollection.set.MutableOrderedSet
import org.junit.jupiter.api.Test
import kotlin.test.*
import kotlin.test.assertContentEquals

interface OrderedSetTests<E : Comparable<E>> {
    val set: MutableOrderedSet<E>
    val infiniteSeq: Sequence<E>

    @Test
    fun inOrderAddAll() {
        val list = infiniteSeq.distinct().take(10).shuffled().toList()
        set.addAll(list)
        assertContentEquals(list.sorted(), set)
    }

    @Test
    fun noDuplicateItems() {
        val value = infiniteSeq.first()
        set.add(value)
        set.add(value)
        assertContentEquals(listOf(value), set)
    }

    @Test
    fun size() {
        assertEquals(0, set.size)
        val value = infiniteSeq.first()
        set.add(value)
        assertEquals(1, set.size)
    }

    @Test
    fun containsNothing() {
        val value = infiniteSeq.first()
        assertFalse {
            set.contains(value)
        }
    }

    @Test
    fun containsOne() {
        val value = infiniteSeq.first()
        set.add(value)
        assertTrue { set.contains(value) }
    }

}