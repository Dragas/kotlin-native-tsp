private data class ClosestNode(val node: Node, val distance: Int)

class Greedy(private val graph: Graph) {
    private val visitedNodes: MutableSet<Node> = mutableSetOf()

    private fun getClosest(node: Node): ClosestNode? {
        val nodes = node.neighbors
                .filter { it.value != 0 && !visitedNodes.contains(it.key) }
        val clostest = nodes
                .minBy { it.value }!!
        return ClosestNode(clostest.key, clostest.value)
    }

    fun solve() {
        var previousNode = graph.first()
        visitedNodes.add(graph.first())
        var cost = 0

        while (visitedNodes.size != graph.size) {
            val closest = getClosest(previousNode)!!
            visitedNodes.add(closest.node)
            cost += closest.distance
            previousNode = closest.node
        }

        cost += visitedNodes.last().neighbors[graph.first()]!!
        println("Final cost: $cost")
    }
}