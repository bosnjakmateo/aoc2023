fun day03PartOne(input: List<String>): Int {
    val elements = input.flatMapIndexed { x, line -> parseLine(line, x) }

    return elements
        .filterIsInstance<SerialNumber>()
        .filter { number ->
            val surroundingIndexes = number.coordinates.flatMap { it.getSurroundingIndexes() }
            elements.filterNot { it == number }.any { it is Symbol && surroundingIndexes.contains(it.coordinate) }
        }.sumOf { it.value }
}

fun day03PartTwo(input: List<String>): Int {
    val elements = input.flatMapIndexed { x, line -> parseLine(line, x) }

    return elements
        .asSequence()
        .filterIsInstance<Symbol>()
        .filter { it.value == "*" }
        .map { it.coordinate.getSurroundingIndexes() }
        .map { surroundingGearIndexes ->
            elements
                .filterIsInstance<SerialNumber>()
                .filter { serialNumber -> serialNumber.coordinates.any { surroundingGearIndexes.contains(it) } }
        }.filter { it.size == 2 }
        .sumOf { it[0].value * it[1].value }
}

private fun parseLine(line: String, x: Int): List<Element> {
    val serialNumber = mutableListOf<Char>()
    val elements = mutableListOf<Element>()

    line.forEachIndexed { i, char ->
        if (char.isDigit()) {
            serialNumber.add(char)
            return@forEachIndexed
        }

        if (serialNumber.isNotEmpty()) {
            createSerialNumber(serialNumber, i, x).also { elements.add(it) }
            serialNumber.clear()
        }

        if (char == '.') {
            return@forEachIndexed
        }

        elements.add(Symbol(line[i].toString(), Coordinate(x, i)))
    }

    if (serialNumber.isNotEmpty()) {
        createSerialNumber(serialNumber, line.lastIndex, x).also { elements.add(it); serialNumber.clear() }
    }

    return elements
}

private fun createSerialNumber(serialNumber: MutableList<Char>, i: Int, x: Int) = SerialNumber(
    serialNumber.joinToString("").toInt(),
    (i - serialNumber.size..<i).map { Coordinate(x, it) }
)

interface Element

data class SerialNumber(val value: Int, val coordinates: List<Coordinate>) : Element
data class Symbol(val value: String, val coordinate: Coordinate) : Element

fun main() {
    val input = DataParser.parseStrings("day03.txt")

    println("Solution part one: ${day03PartOne(input)}")
    println("Solution part two: ${day03PartTwo(input)}")
}