/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package orderedCollection.list

import orderedCollection.OrderedCollection


interface OrderedList<E> : OrderedCollection<E>, List<E>

interface MutableOrderedList<E> : OrderedCollection<E>, MutableCollection<E>,
    List<E>