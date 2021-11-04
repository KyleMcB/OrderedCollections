/*
 * Copyright (c) 2021. Kyle McBurnett
 */

package orderedCollection.tree

class BSTree<K : Comparable<K>, V>(
    override val insertMode: Tree.InsertMode = Tree.InsertMode.REPLACE
) : Tree<K, V>, AbstractCollection<Pair<K, V>>() {
    override fun headSet(key: K): Iterable<Pair<K, V>> {
        // find the first node that is greater than or equal to key
        var closeOrNode = findOrWouldBeParent(key)
        while ((closeOrNode?.compareTo(key)
                ?: 1) < 0
        ) closeOrNode = closeOrNode!!.inOrderSuccessor()

        return object : Iterable<Pair<K, V>> {
            /** Returns an iterator over the elements of this object. */
            override fun iterator(): Iterator<Pair<K, V>> {
                return _Iterator(closeOrNode)
            }
        }
    }

    protected fun findOrWouldBeParent(key: K): Node<K, V>? {
        var prev = root ?: return null
        var current = root
        while (current != null) {
            val difference = current.compareTo(key)
            if (difference < 0) {
                // this node is to the left of the key
                prev = current
                current = current.right
            } else if (difference > 0) {
                // this node is to the right of the key
                prev = current
                current = current.left
            } else {
                // keys match
                return current
            }
        }
        return prev
    }

    data class Node<K : Comparable<K>, V>(
        val key: K,
        val value: V,
        var parent: Node<K, V>?,
        var left: Node<K, V>? = null,
        var right: Node<K, V>? = null
    ) : Comparable<K> {
        /**
         * Compares this object with the specified object for order. Returns zero if this object is
         * equal to the specified [other] object, a negative number if it's less than [other], or a
         * positive number if it's greater than [other].
         */
        override fun compareTo(other: K): Int {
            return key.compareTo(other)
        }
    }

    fun Node<K, V>.inOrderSuccessor(): Node<K, V>? {
        if (right != null) return leftMostNode(right!!)
        else if (parent != null) {
            var p = parent
            var node: Node<K, V>? = this
            while (p != null && p.right === node) {
                node = p
                p = p.parent
            }
            return p
        } else return null
    }

    private var root: Node<K, V>? = null
    private var _size: Int = 0
    override val size: Int
        get() = _size

    override fun iterator(): Iterator<Pair<K, V>> {
        return _Iterator()
    }

    //    inner class  _iterator<K:Comparable<K>,V>(var current:Node = leftMostNode())
    inner class _Iterator(var current: Node<K, V>? = leftMostNode(), val end: Node<K, V>? = rightMostNode()) :
        Iterator<Pair<K, V>> {

        /** Returns `true` if the iteration has more elements. */
        override fun hasNext(): Boolean {
            if (current == null || current == end) return false
            return true
        }

        /** Returns the next element in the iteration. */
        override fun next(): Pair<K, V> {
            val result = current
            current = current?.inOrderSuccessor()
            if (result != null) {
                return result.key to result.value
            }
            throw NoSuchElementException()
        }
    }

    private fun leftMostNode(): Node<K, V>? {
        if (root == null) return null else return leftMostNode(root!!)
    }

    private fun rightMostNode(): Node<K, V>? {
        if (root == null) return null else return rightMostNode(root!!)
    }

    private fun rightMostNode(node: Node<K, V>): Node<K, V> {
        var current = node
        while (current.right != null) current = current.right!!
        return current
    }

    private fun leftMostNode(node: Node<K, V>): Node<K, V> {

        var current = node
        while (current.left != null) {
            current = current.left!!
        }
        return current
    }

    override fun insert(pair: Pair<K, V>) {
        _size++ // optimistically increase size
        val key = pair.first
        if (root == null) {
            root = Node(pair.first, pair.second, null)
        } else {
            var currentNode = root
            while (currentNode != null) {
                if (key < currentNode.key) {
                    if (currentNode.left == null) {
                        currentNode.left = Node(key, pair.second, currentNode)
                        return
                    } else {
                        currentNode = currentNode.left
                    }
                } else if (key == currentNode.key) {
                    // inserting at a key that already exists
                    _size-- // not increasing size
                    when (insertMode) {
                        Tree.InsertMode.REPLACE -> {
                            val newNode = currentNode.copy(key = pair.first, value = pair.second)
                            // re-hook parent
                            if (currentNode.parent == null) {
                                // we are the root
                                root = newNode
                            }
                            if (currentNode.parent?.left === currentNode) {
                                currentNode.parent?.left = newNode
                            } else currentNode.parent?.right = newNode
                            // re-hook left
                            currentNode.left?.let { it.parent = newNode }
                            // re-hook right
                            currentNode.right?.let { it.parent = newNode }
                        }
                        Tree.InsertMode.IGNORE -> {
                        } // ignore the new element
                    }
                    break
                } else if (key > currentNode.key) {
                    if (currentNode.right == null) {
                        currentNode.right = Node(key, pair.second, currentNode)
                        return
                    } else {
                        currentNode = currentNode.right
                    }
                }
            }
        }
    }

    override fun at(key: K): V {
        return getNode(key)?.value ?: throw NoSuchElementException()
    }

    override fun atOrNull(key: K): V? {
        return getNode(key)?.value
    }

    private fun getNode(key: K): Node<K, V>? {
        if (root == null) return null
        else {

            var currentNode = root
            while (currentNode != null) {
                if (key < currentNode.key) currentNode = currentNode.left
                else if (key == currentNode.key) return currentNode
                else if (key > currentNode.key) currentNode = currentNode.right
            }
            return null
        }
    }

    override fun removeAt(key: K): Boolean {
        val node = getNode(key) ?: return false
        _size-- // optimistically decrease size
        // 3 cases
        // element has zero children
        if (node.left == null && node.right == null) {
            if (node.parent == null) {
                // we are root
                root = null
            } else {
                val child = if (node.left == null) node.right else node.left
                if (node.parent!!.left === node) {
                    node.parent!!.left = child
                } else node.parent!!.right = child
            }
        }
        // element has one child
        else if (node.left == null || node.right == null) {
            val child = if (node.left == null) node.right else node.left
            if (node.parent == null) {
                // we are root
                root = child
            } else {
                if (node.parent!!.left === node) {
                    node.parent!!.left = child
                } else {
                    node.parent!!.right = child
                }
            }
        }
        // element has two children
        else {
            // get the inorder successor to replace this node
            val succ = node.inOrderSuccessor()!!
            if (succ.parent !== node)
                succ.parent!!.left = succ.right // this is a successor, so we know it has a parent
            if (node.left !== succ) succ.left = node.left
            if (node.right !== succ) succ.right = node.right
            if (node.left != null) node.left!!.parent = succ
            if (node.right != null) node.right!!.parent = succ

            if (node.parent == null) {
                root = succ
                succ.parent = null
            } else {
                if (node.parent!!.left === node) node.parent!!.left = succ
                else node.parent!!.right = succ
                succ.parent = node.parent
            }
        }
        return true
    }

    override fun tailSet(key: K): Iterable<Pair<K, V>> {
        var closeOrEnd = findOrWouldBeParent(key)
        while (closeOrEnd?.compareTo(key) ?: 1 < 0) closeOrEnd = closeOrEnd!!.inOrderSuccessor()
        //once more for the "once past the end" style of iterators
        closeOrEnd = closeOrEnd?.inOrderSuccessor()
        return object : Iterable<Pair<K, V>> {
            /**
             * Returns an iterator over the elements of this object.
             */
            override fun iterator(): Iterator<Pair<K, V>> {
                return _Iterator(end = closeOrEnd)
            }
        }
    }

    override fun subList(start: K, end: K): Iterable<Pair<K, V>> {
        TODO("Not yet implemented")
    }
}
