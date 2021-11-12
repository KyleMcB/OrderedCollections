# OrderedCollections

---

I love kotlin, but coming from C++ I feel the stdlib is lacking a few thing. Naming Collections that maintain order. So
I made some.

This library consists of three modules the interface, the testing interface, and a basic implementation. If you use the
interface to create your own OrederedCollection then I encourage you to use the test interface like this

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

Current basic implementations use a List and keep it sorted on insertion using List.binarysearch or List.sort for add
and addAll respectively. So addAll might be an expensive operation. I would like to implement a red-black tree later to
mitigate that.

