/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package orderedCollection

interface OrderedCollection<E, C : Comparator<E>> : Collection<E> {}

interface MutableOrderedCollection<E, C : Comparator<E>> : OrderedCollection<E, C>, MutableCollection<E>