import orderedCollection.list.OrderedList

class Bla : EmptyOrderedListTests<Int> {
    override val list: OrderedList<Int>
        get() = object : OrderedList<Int> {
            override val size: Int
                get() = TODO("Not yet implemented")

            override fun contains(element: Int): Boolean {
                TODO("Not yet implemented")
            }

            override fun containsAll(elements: Collection<Int>): Boolean {
                TODO("Not yet implemented")
            }

            override fun isEmpty(): Boolean {
                TODO("Not yet implemented")
            }

            override fun iterator(): Iterator<Int> {
                TODO("Not yet implemented")
            }

            override fun get(index: Int): Int {
                TODO("Not yet implemented")
            }

            override fun indexOf(element: Int): Int {
                TODO("Not yet implemented")
            }

            override fun lastIndexOf(element: Int): Int {
                TODO("Not yet implemented")
            }

            override fun listIterator(): ListIterator<Int> {
                TODO("Not yet implemented")
            }

            override fun listIterator(index: Int): ListIterator<Int> {
                TODO("Not yet implemented")
            }

            override fun subList(fromIndex: Int, toIndex: Int): List<Int> {
                TODO("Not yet implemented")
            }
        }
}