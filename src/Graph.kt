class Graph {
    val nodes: MutableList<Node> = mutableListOf()
    val size
        get() = nodes.size

    fun first(): Node = nodes.first()

    operator fun get(nodeId: Int): Node = nodes.getOrElse(nodeId) { nodes.add(it, Node(it)) ; nodes[it] }
}