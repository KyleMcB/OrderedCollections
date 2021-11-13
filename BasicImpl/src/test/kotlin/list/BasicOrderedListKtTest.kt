/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package list

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertContentEquals

internal class BasicOrderedListKtTest {

    @Test
    fun orderedListOf() {
        val list = orderedListOf<Int>()
        assertContentEquals(emptyList(), list)
    }

    @Test
    fun OrderedListOfWithArgs() {
        val list = orderedListOf(5, 3, 2, 1, 4)
        assertContentEquals(listOf(1, 2, 3, 4, 5), list)
    }

    @Test
    fun mutableOrderedListOf() {
        val list = mutableOrderedListOf<Int>()
        list.apply { add(5);add(1);add(2) }
        assertContentEquals(listOf(1, 2, 5), list)
    }

    @Test
    fun MutableOrderedListOfWithArgs() {
        val list = mutableOrderedListOf(5, 3, 2, 1, 4)
        assertContentEquals(listOf(1, 2, 3, 4, 5), list)
    }

    @Test
    fun toOrderedList() {
        val list = listOf(5, 2, 3, 1, 4).toOrderedList()
        assertContentEquals(listOf(1, 2, 3, 4, 5), list)
    }

    @Test
    fun toMutableOrderedList() {
        val list = listOf(3, 5, 1, 2, 4).toMutableOrderedList()
        assertContentEquals(listOf(1, 2, 3, 4, 5), list)
    }
}