# OrderedCollections

---
[![](https://jitpack.io/v/KyleMcB/OrderedCollections.svg)](https://jitpack.io/#KyleMcB/OrderedCollections)
[![wakatime](https://wakatime.com/badge/user/cca5596a-2916-4d3a-8bcb-cdd652e25cf1/project/83ebdd9f-3e24-4a9a-95f4-53508d7ec40b.svg)](https://wakatime.com/badge/user/cca5596a-2916-4d3a-8bcb-cdd652e25cf1/project/83ebdd9f-3e24-4a9a-95f4-53508d7ec40b)
[![Build Status](https://app.travis-ci.com/KyleMcB/OrderedCollections.svg?branch=main)](https://app.travis-ci.com/KyleMcB/OrderedCollections)

I love kotlin, but coming from C++ I feel the stdlib is lacking a few things. Namely Collections that maintain order. So
I made some.

This library consists of three modules the interface, the testing interface, and a basic implementation. To install and
use: use jitpack.io to add the dependency like this

```kotlin
repositories {
    mavenCentral()
    maven(url = "https://jitpack.io")
}
dependencies {
    implementation("com.github.KyleMcB.OrderedCollections:core:listandmapbeta2")
    implementation("com.github.KyleMcB.OrderedCollections:impl:listandmapbeta2")
}
```

Then you can use in a similar fashion to other data structures from the Kotlin stdlib. Like this...

```kotlin
val sortedList = listOf(5, 2, 1, 3, 4).toOrderedList()
val mutableSortedList = listOf(3, 24, 1, 2).toMutableOrderedList()
val anotherSortedList = orderedListOf(3, 4, 1, 2)
val finalSortedList = mutableOrderedListOf(5, 4, 2, 1)

val sortedMap = listOf(5 to 5, 2 to 2, 1 to 1).toOrderedMap()
val alwaysSortedMap = mutableOrderedMapOf(2 to 2, 1 to 1)
val value = alwaysSortedMap.at(2) // return 2
```

The map and list both use a list internally and keep it sorted on insertion using binarySearch or just plain sorting.
The map is also an iterable collection of pairs of key to value. I think it is strange the kotlin stdlib map does not
inherit from Collection<E>. A red black tree would be better, but require much more development work. I will do that...
I want to get a good implementation right now to use in my ToDone app. Also I'll make an OrderedSet soon because it
should exist. I just don't need one right now.

If you use the interface to create your own OrederedCollection then I encourage you to use the test interface like this

```kotlin
class MyOrderedList : MutableOrderedList<Int> {
    override val comparator = naturalOrder()
    ...
}
```

Then test your implementation like this

```kotlin
import testsuite.map.MutableOrderedMapTests
import testsuite.list.EmptyOrderedListTests

class MyOrderedListTests : EmptyOrderedListTests<Int> {
    override val list: OrderedList<Int> = BasicOrderedList(naturalOrder())
    override val exampleValue: Int
        get() = 1
}
class MutableMyOrderedListTests : MutableOrderedListTests<Int> {
    override val list: MutableOrderedList<Int> = BasicOrderedList(naturalOrder())
    override val values: Sequence<Int> = generateSequence { (0..9999).random() }
}
```

The test suite does not care about the specific implementation of the orderedList|Map. Just assign the your
implementation to the override list property and give it an example value, and an infinite sequence of values in the
mutable tests. If your implementation has persistant storage I suggest clearing that in a setup function or init block.



