/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package set

import orderedCollection.set.MutableOrderedSet

class BasicMutableOrderedSet<E>(override val comparator: Comparator<E>, val set: MutableList<E> = mutableListOf()) :
    MutableOrderedSet<E>, MutableCollection<E> by set {
    override fun add(element: E): Boolean {
        val index = set.binarySearch(element, comparator)
        return if (index < 0) {
            //not in set
            val positiveIndex = -(index + 1)
            set.add(positiveIndex, element)
            true
        } else {
            //in set
            false
        }
    }


    override fun addAll(elements: Collection<E>): Boolean {
        val before = size
        for (element in elements) {
            add(element)
        }
        val after = size
        return before < after
    }


}