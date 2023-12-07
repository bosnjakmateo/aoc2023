object Day01 {
    fun solution(input: List<String>): Int {
        return input.sumOf { combineFirstAndLastDigit(it) }
    }

    private fun combineFirstAndLastDigit(line: String): Int {
        val digitIndex = line.mapIndexedNotNull { index, c -> if (c.isDigit()) index to c.toString() else null }
        val wordDigitIndex = NUMBERS_TO_WORDS.flatMap { entry ->
            listOf(
                line.indexOf(entry.key) to entry.value,
                line.lastIndexOf(entry.key) to entry.value,
            ).filter { it.first != -1 }
        }

        val sortedIndexedDigits = (digitIndex + wordDigitIndex).sortedBy { it.first }

        return "${sortedIndexedDigits.first.second}${sortedIndexedDigits.last.second}".toInt()
    }

    private val NUMBERS_TO_WORDS = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9",
    )
}

fun main() {
    val input = DataParser.parseStrings("day01.txt")

    println("Solution: " + Day01.solution(input))
}