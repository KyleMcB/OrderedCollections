/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package orderedCollection

interface OrderedCollection<E> : Collection<E> {
    val comparator: Comparator<E>
}

interface MutableOrderedCollection<E> : OrderedCollection<E>, MutableCollection<E>