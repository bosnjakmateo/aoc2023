object Day07 {
    fun partOne(input: String): Long {
        return parsePartOne(input)
            .sortedWith(HandComparator("23456789TJQKA"))
            .mapIndexed { index, hand -> (index + 1) * hand.bidAmount }
            .sum()
    }

    fun partTwo(input: String): Long {
        return parsePartTwo(input)
            .sortedWith(HandComparator("J23456789TQKA"))
            .mapIndexed { index, hand -> (index + 1) * hand.bidAmount }
            .sum()
    }

    private fun parsePartOne(input: String): List<Hand> {
        return input.lines().map { line ->
            val cards = line.take(5)
            Hand(cards, calculateHandStrength(cards), line.drop(6).toLong())
        }
    }

    private fun parsePartTwo(input: String): List<Hand> {
        return input.lines().map { line ->
            val cards = line.take(5)
            val bidAmount = line.drop(6).toLong()
            val jokerCount = cards.count { it == 'J' }

            if (jokerCount == 0 || jokerCount == 5) {
                return@map Hand(cards, calculateHandStrength(cards), bidAmount)
            }

            val jokerTarget = cards.filterNot { it == 'J' }.groupingBy { it }.eachCount().maxBy { it.value }.key

            Hand(cards, calculateHandStrength(cards.replace('J', jokerTarget)), bidAmount)
        }
    }

    private fun calculateHandStrength(cards: String): Int {
        val cardsCount = cards.groupingBy { it }.eachCount().map { it.value }.sorted()
        return when (cardsCount) {
            listOf(1, 1, 1, 2) -> 1
            listOf(1, 2, 2) -> 2
            listOf(1, 1, 3) -> 3
            listOf(2, 3) -> 4
            listOf(1, 4) -> 5
            listOf(5) -> 6
            else -> 0
        }
    }
}

data class Hand(val cards: String, val strength: Int, val bidAmount: Long)

class HandComparator(private val cardStrength: String) : Comparator<Hand> {
    override fun compare(hand1: Hand, hand2: Hand): Int {
        if (hand1.strength > hand2.strength) return 1
        if (hand2.strength > hand1.strength) return -1

        for (pair in hand1.cards.zip(hand2.cards)) {
            val card1 = cardStrength.indexOf(pair.first)
            val card2 = cardStrength.indexOf(pair.second)

            if (card1 > card2) return 1
            if (card2 > card1) return -1
        }

        return 0
    }
}

fun main() {
    val input = DataParser.parseString("day07.txt")

    println("Solution part one: " + Day07.partOne(input))
    println("Solution part two: " + Day07.partTwo(input))
}