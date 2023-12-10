object Day09 {
    fun partOne(input: String): Int {
        return parse(input).sumOf { history -> predictNextValue(history).last }
    }

    fun partTwo(input: String): Int {
        return parse(input).sumOf { history -> predictNextValue(history).first }
    }

    private fun predictNextValue(history: List<Int>): Prediction {
        val layers = generateSequence(history) { currentLayer ->
            generateNextLayer(currentLayer).takeUnless { layer -> layer.all { it == 0 } }
        }.toList()

        return layers.reversed().fold(Prediction(0, 0)) { acc, layer ->
            Prediction(layer.first() - acc.first, acc.last + layer.last())
        }
    }

    private fun generateNextLayer(layer: List<Int>): List<Int> {
        return layer.windowed(2).map { it.last() - it.first() }
    }

    private fun parse(input: String): List<List<Int>> {
        return input.lines().map { it.split(" ").map { it.toInt() } }
    }
}

data class Prediction(var first: Int, var last: Int)

fun main() {
    val input = DataParser.parseString("day09.txt")

    println("Solution part one: " + Day09.partOne(input))
    println("Solution part two: " + Day09.partTwo(input))
}