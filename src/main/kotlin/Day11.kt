import kotlin.math.abs
import kotlin.math.max
import kotlin.math.min

object Day11 {
    fun partOne(input: List<String>, expansionSize: Long = 1): Long {
        val galaxyMap = input
            .map { line -> line.toCharArray() }
            .toTypedArray()
            .let { expandGalaxies(it) }

        val galaxiesDistance = mutableMapOf<Pair<Coordinate, Coordinate>, Long>()
        val galaxies = getGalaxyCoordinates(galaxyMap)

        for (galaxyA in galaxies) {
            for (galaxyB in galaxies) {
                if (galaxyA == galaxyB) continue
                if (galaxiesDistance.contains(Pair(galaxyA, galaxyB))
                    || galaxiesDistance.contains(Pair(galaxyB, galaxyA))
                ) continue

                galaxiesDistance[Pair(galaxyA, galaxyB)] = getManhattanDistance(galaxyMap, galaxyA, galaxyB, expansionSize)
            }
        }

        return galaxiesDistance.values.sum()
    }

    private fun getManhattanDistance(
        galaxyMap: Array<CharArray>,
        start: Coordinate,
        end: Coordinate,
        expansionSize: Long,
    ): Long {
        val xMax = max(start.x, end.x)
        val xMin = min(start.x, end.x)
        var xWormhole = 0

        for (x in (xMin..xMax)) {
            if (galaxyMap[start.y][x] == '~') xWormhole++
        }

        val yMax = max(start.y, end.y)
        val yMin = min(start.y, end.y)
        var yWormhole = 0

        for (y in (yMin..yMax)) {
            if (galaxyMap[y][start.x] == '~') yWormhole++
        }

        return abs(start.x - end.x) + abs(start.y - end.y) + (xWormhole * expansionSize) + (yWormhole * expansionSize)
    }

    private fun getGalaxyCoordinates(galaxyMap: Array<CharArray>): Set<Coordinate> {
        return galaxyMap.flatMapIndexed { y, row ->
            row.mapIndexed { x, cell -> if (cell == '#') Coordinate(x, y) else null }
        }.filterNotNull().toSet()
    }

    private fun expandGalaxies(galaxyMap: Array<CharArray>): Array<CharArray> {
        val xIndices = galaxyMap[0].indices.filter { x ->
            galaxyMap.all { row -> row[x] != '#' }
        }

        return galaxyMap
            .map { row ->
                if (row.all(Char::isDot)) "~".repeat(row.size).toCharArray()
                else row.copyOf()
            }.onEach { row ->
                xIndices.forEach { x -> row[x] = '~' }
            }.toTypedArray()
    }
}

fun main() {
    val input = DataParser.parseStrings("day11.txt")

    println("Solution part one: " + Day11.partOne(input))
    println("Solution part two: " + Day11.partOne(input, 1_000_000 - 1))
}