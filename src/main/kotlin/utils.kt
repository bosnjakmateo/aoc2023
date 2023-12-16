data class Coordinate(
    val x: Int,
    val y: Int,
) {
    fun getSurroundingIndexes(): List<Coordinate> {
        return (-1..1).flatMap { i ->
            (-1..1).map { j ->
                Coordinate(x + i, y + j)
            }
        }
    }
}

fun Array<CharArray>.getValueSafe(x: Int, y: Int): Char? {
    if (x > this[0].size - 1 || x < 0 || y > this.size - 1 || y < 0) return null
    return this[y][x]
}

fun lcm(numbers: List<Long>): Long = numbers.reduce { acc, num -> acc * num / gcd(acc, num) }

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)