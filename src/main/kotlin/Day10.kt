import Day10.Direction.*

object Day10 {
    fun partOne(input: List<String>): Int {
        val (pipes, startY, startX) = parse(input)

        return visitPipes(startX, startY, pipes).size / 2
    }

    fun partTwo(input: List<String>): Int {
        val (pipes, startY, startX) = parse(input)

        val coordinates = visitPipes(startX, startY, pipes)
        setNonLoopPipesToDot(pipes, coordinates)

        var count = 0

        for (y in pipes.indices) {
            var within = false
            var up: Boolean? = null

            for (x in pipes[0].indices) {
                val pipe = pipes[y][x]
                when {
                    pipe == '|' -> within = !within
                    pipe == '-' -> continue
                    "LF".contains(pipe) -> up = pipe == 'L'
                    "7J".contains(pipe) -> {
                        if (pipe != ('J'.takeIf { up == true } ?: '7')) {
                            within = !within
                        }
                        up = null
                    }
                }
                if (within && !coordinates.contains(Coordinate(x, y))) {
                    count++
                }
            }
        }

        return count
    }

    private fun setNonLoopPipesToDot(pipes: Array<CharArray>, coordinates: MutableList<Coordinate>) {
        for (y in pipes.indices) {
            for (x in pipes[0].indices) {
                if (!coordinates.contains(Coordinate(x, y))) {
                    pipes[y][x] = '.'
                }
            }
        }
    }

    private fun visitPipes(x: Int, y: Int, pipes: Array<CharArray>): MutableList<Coordinate> {
        val visitedPipes = mutableListOf(Coordinate(x, y))

        var currentX = x
        var currentY = y
        var currentDirection = Direction.entries.firstNotNullOf { navigationDirection[Pair(pipes[y][x], it)] }

        while (true) {
            when (currentDirection) {
                RIGHT -> currentX++
                UP -> currentY--
                LEFT -> currentX--
                DOWN -> currentY++
            }

            val pipe = pipes[currentY][currentX]

            if (visitedPipes.contains(Coordinate(currentX, currentY))) break
            visitedPipes.add(Coordinate(currentX, currentY))

            currentDirection = navigationDirection[Pair(pipe, currentDirection)]!!
        }

        return visitedPipes
    }

    private fun parse(input: List<String>): Triple<Array<CharArray>, Int, Int> {
        val pipes = input.map { line -> line.toCharArray() }.toTypedArray()
        val y = input.indexOfFirst { it.contains('S') }
        val x = input[y].indexOf('S')

        fixStart(x, y, pipes)
        return Triple(pipes, y, x)
    }

    private fun fixStart(x: Int, y: Int, pipes: Array<CharArray>) {
        val right = pipes.getValueSafe(x + 1, y)
        val up = pipes.getValueSafe(x, y - 1)
        val left = pipes.getValueSafe(x - 1, y)
        val down = pipes.getValueSafe(x, y + 1)

        val fixedStart = when {
            navigationDirection.contains(Pair(right, RIGHT)) && navigationDirection.contains(Pair(left, LEFT)) -> '-'
            navigationDirection.contains(Pair(up, UP)) && navigationDirection.contains(Pair(down, DOWN)) -> '|'
            navigationDirection.contains(Pair(up, UP)) && navigationDirection.contains(Pair(right, RIGHT)) -> 'L'
            navigationDirection.contains(Pair(up, UP)) && navigationDirection.contains(Pair(left, LEFT)) -> 'J'
            navigationDirection.contains(Pair(left, LEFT)) && navigationDirection.contains(Pair(down, DOWN)) -> '7'
            navigationDirection.contains(Pair(right, RIGHT)) && navigationDirection.contains(Pair(down, DOWN)) -> 'F'
            else -> error("This should not happen")
        }

        pipes[y][x] = fixedStart
    }

    private val navigationDirection = mapOf(
        Pair('|', DOWN) to DOWN,
        Pair('|', UP) to UP,
        Pair('-', RIGHT) to RIGHT,
        Pair('-', LEFT) to LEFT,
        Pair('L', LEFT) to UP,
        Pair('L', DOWN) to RIGHT,
        Pair('J', DOWN) to LEFT,
        Pair('J', RIGHT) to UP,
        Pair('7', RIGHT) to DOWN,
        Pair('7', UP) to LEFT,
        Pair('F', LEFT) to DOWN,
        Pair('F', UP) to RIGHT
    )

    enum class Direction {
        RIGHT,
        LEFT,
        DOWN,
        UP,
        ;
    }
}

fun main() {
    val input = DataParser.parseStrings("day10.txt")

    println("Solution part one: " + Day10.partOne(input))
    println("Solution part two: " + Day10.partTwo(input))
}