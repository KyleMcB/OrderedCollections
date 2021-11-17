/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package testsuite.set

import orderedCollection.set.MutableOrderedSet
import org.junit.jupiter.api.Test
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
}