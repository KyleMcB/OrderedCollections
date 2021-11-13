/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package map

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.test.assertContentEquals

internal class BasicOrderedMapKtTest {
    val data = listOf(5 to 5, 2 to 2, 1 to 1, 3 to 3, 4 to 4).shuffled()
    val sorted = data.sortedBy { it.first }

    @Test
    fun orderedMapOftest() {
        val list = orderedMapOf(*data.toTypedArray())
        assertContentEquals(sorted, list)
    }

    @Test
    fun mutableOrderedMapOfTest() {
        val list = mutableOrderedMapOf(*data.toTypedArray())
        assertContentEquals(sorted, list)
    }

    @Test
    fun toOrderedMap() {
        val list = data.toOrderedMap()
        assertContentEquals(sorted, list)
    }

    @Test
    fun toMutableOrderedMap() {
        val list = data.toMutableOrderedMap()
        assertContentEquals(sorted, list)
    }

    @Test
    fun emptymap() {
        val list = orderedMapOf<Int, Int>()
        assertContentEquals(emptyList(), list)
    }

    @Test
    fun mutablemapoftest() {
        val list = mutableOrderedMapOf<Int, Int>()
        list.addAll(data)
        assertContentEquals(sorted, list)
    }
}