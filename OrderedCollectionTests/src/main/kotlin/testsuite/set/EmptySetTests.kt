/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package testsuite.set

import orderedCollection.set.OrderedSet
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

interface EmptySetTests<E : Comparable<E>> {
    val set: OrderedSet<E>
    val exampleValue: E

    @Test
    fun sizeZero() {
        assertEquals(0, set.size)
    }
}