object Day08 {
    fun partOne(input: String): Int {
        val (navigation, directionMap) = parse(input)
        val navigationComputer = NavigationComputer(navigation, directionMap, "AAA")

        while (navigationComputer.currentPoint != "ZZZ") {
            navigationComputer.move()
        }

        return navigationComputer.step
    }

    fun partTwo(input: String): Long {
        val (navigation, directionMap) = parse(input)
        val startingPoints = directionMap.keys.filter { it.last() == 'A' }

        val navigationComputers = startingPoints.map { NavigationComputer(navigation, directionMap, it) }

        while (navigationComputers.any(NavigationComputer::proccesing)) {
            navigationComputers.forEach(NavigationComputer::move)
        }

        return lcm(navigationComputers.map { it.step.toLong() })
    }

    private fun parse(input: String): Pair<String, Map<String, Direction>> {
        val navigation = input.lines()[0]
        val directionMap = input.lines().drop(2).associateBy(
            { it.take(3) },
            {
                val split = it.takeLast(10).removeSurrounding("(", ")").split(",")
                Direction(split[0].trim(), split[1].trim())
            }
        )
        return Pair(navigation, directionMap)
    }
}

data class NavigationComputer(
    private val navigation: String,
    private val directionMap: Map<String, Direction>,
    private val startingPoint: String
) {
    private var currentDirection = directionMap[startingPoint]!!
    var step = 0
    var currentPoint = ""
    var proccesing = true

    fun move() {
        if (!proccesing) return

        val nextTurn = navigation[step % navigation.length]
        val (nextPoint, newDirection) = getNextDirection(nextTurn, currentDirection, directionMap)

        currentDirection = newDirection
        currentPoint = nextPoint
        step++

        if (nextPoint.last() == 'Z') proccesing = false
    }

    private fun getNextDirection(
        nextTurn: Char,
        direction: Direction,
        directionMap: Map<String, Direction>
    ): Pair<String, Direction> {
        return (direction.left to directionMap.getValue(direction.left)).takeIf { nextTurn == 'L' }
            ?: (direction.right to directionMap.getValue(direction.right))
    }
}

data class Direction(val left: String, val right: String)

fun main() {
    val input = DataParser.parseString("day08.txt")

    println("Solution part one: " + Day08.partOne(input))
    println("Solution part two: " + Day08.partTwo(input))
}