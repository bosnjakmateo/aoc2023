fun day02PartOne(input: List<String>): Int {
    return input
        .map { parse(it) }
        .filter { isGamePossible(it) }
        .sumOf { it.id }
}

private fun isGamePossible(game: Game): Boolean {
    return game.setsOfCubes.all { set ->
        set.all cube@{ cube ->
            when {
                cube.second == "red" && cube.first > 12 -> return false
                cube.second == "green" && cube.first > 13 -> return false
                cube.second == "blue" && cube.first > 14 -> return false
                else -> return@cube true
            }
        }
    }
}

fun day02PartTwo(input: List<String>): Int {
    return input
        .map { parse(it) }
        .sumOf { calculatePowerOfCubes(it) }
}

private fun calculatePowerOfCubes(game: Game): Int {
    return game.setsOfCubes
        .flatten()
        .groupBy({ it.second }) { it.first }.entries
        .map { it.value.max() }
        .fold(1) { acc, i -> acc * i }
}

private fun parse(rawGame: String): Game {
    val (gameIdRaw, rest) = rawGame.split(":").let { it.first to it.last }
    val gameId = gameIdRaw.split(" ").last.toInt()

    val sets = rest.split(";").map { set ->
        set.split(",").map { cube ->
            val (number, color) = cube.trim().split(" ").let { it.first to it.last }
            Pair(number.toInt(), color)
        }
    }

    return Game(gameId, sets)
}

data class Game(
    val id: Int,
    val setsOfCubes: List<List<Pair<Int, String>>>
)

fun main() {
    val input = DataParser.parseStrings("day02.txt")

    println("Solution part one: ${day02PartOne(input)}")
    println("Solution part two: ${day02PartTwo(input)}")
}