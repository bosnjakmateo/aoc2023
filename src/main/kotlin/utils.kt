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