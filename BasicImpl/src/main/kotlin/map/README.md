# OrderedMap

---

The OrderedMap uses the MutableCollection interface. I think it is very strange the kotlin stdlib map does not implement
any collection interfaces. So you can call add(element) or contains(element) or iterate over the collection like any
other collection. Here an element is a key to value Pair. So if you iterate you will get an ordered by key list of Pair<
key,valur> items.

For performant item lookup, I went with interface that looks a little like C++ map interface. to get a value with O(log(
N)) use at(key) or atOrNull(key)

The convenience functions that look like the standard library require the key to implement Comparable. So if you want to
use an OrderedMap with a key that does not implement Comparable you can pass a Comparator to the constructor

```kotlin
val comparator = compareBy<String> { it.length }
val map = BasicOrderedMap<String, String>(comparator)
```

Sorry for such a contrived example. I can't think of why you wouldn't want a comparable key, but you can!

examples

```kotlin
val map = mutableOrderedMapOf(2 to "world", 1 to "hello")
map.forEach { println(it.second) }
//hello
//world

```