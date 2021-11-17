/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package set

import orderedCollection.set.OrderedSet

class BasicOrderedSet<E>(override val comparator: Comparator<E>, data: MutableList<E> = mutableListOf<E>()) :
    OrderedSet<E>, Collection<E> by data {
    init {
        data.sortWith(comparator)
    }
}