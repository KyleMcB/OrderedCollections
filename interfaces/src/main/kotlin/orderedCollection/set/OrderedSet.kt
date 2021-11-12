package orderedCollection.set

import orderedCollection.MutableOrderedCollection
import orderedCollection.OrderedCollection


interface OrderedSet<E> : OrderedCollection<E>, Set<E>

interface MutableOrderedSet<E> : OrderedSet<E>, MutableOrderedCollection<E>