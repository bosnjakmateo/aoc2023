fun day04PartOne(input: List<String>): Int {
    return input
        .map { parseLine(it) }
        .map { it.numbers.intersect(it.winningNumbersCount.toSet()) }
        .sumOf { it.fold(0.5) { acc, _ -> acc * 2 }.toInt() }
}

fun day04PartTwo(input: List<String>): Int {
    val cardReports = input
        .mapIndexed { i, l -> parseLine(l, i + 1) }
        .map { CardReport(it.number, it.numbers.intersect(it.winningNumbersCount.toSet()).count()) }

    return calculateTotalScratchCards(cardReports, cardReports)
}

private fun calculateTotalScratchCards(winningCardReports: List<CardReport>, cardReportCopies: List<CardReport>): Int {
    if (cardReportCopies.isEmpty()) {
        return 0
    }

    val newCardReports = mutableListOf<CardReport>()

    for (cardReportCopy in cardReportCopies) {
        for (cardReport in (cardReportCopy.number + 1..cardReportCopy.winningNumbers + cardReportCopy.number)) {
            winningCardReports.first { it.number == cardReport }.also { newCardReports.add(it) }
        }
    }

    return cardReportCopies.size + (calculateTotalScratchCards(winningCardReports, newCardReports))
}

private fun parseLine(line: String, index: Int = 0): Card {
    val rawCards = line.drop(line.indexOf(":") + 1).trim().split("|")

    return Card(
        index,
        rawCards[0].split(" ").filter(String::isNotBlank).map { it.toInt() },
        rawCards[1].split(" ").filter(String::isNotBlank).map { it.toInt() },
    )
}

data class Card(val number: Int, val winningNumbersCount: List<Int>, val numbers: List<Int>)
data class CardReport(val number: Int, val winningNumbers: Int)

fun main() {
    val input = DataParser.parseStrings("day04.txt")

    println("Solution part one: ${day04PartOne(input)}")
    println("Solution part two: ${day04PartTwo(input)}")
}