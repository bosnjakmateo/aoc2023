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

fun lcm(numbers: List<Long>): Long = numbers.reduce { acc, num -> acc * num / gcd(acc, num) }

fun gcd(a: Long, b: Long): Long = if (b == 0L) a else gcd(b, a % b)