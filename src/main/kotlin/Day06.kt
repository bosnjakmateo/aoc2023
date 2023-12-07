object Day06 {
    fun partOne(input: String): Int {
        return parseToRaces(input).map { race ->
            (0..race.time).map { it * (race.time - it) }.count { it > race.distance }
        }.fold(1) { acc, i -> acc * i }
    }

    fun partTwo(input: String): Int {
        return parseToRace(input).let { race ->
            (0..race.time).map { it * (race.time - it) }.count { it > race.distance }
        }
    }

    private fun parseToRaces(input: String): List<Race> {
        val lines = input.trim().lines()

        val timeValues = lines[0].split("\\s+".toRegex()).drop(1).map { it.toLong() }
        val distanceValues = lines[1].split("\\s+".toRegex()).drop(1).map { it.toLong() }

        return timeValues.zip(distanceValues) { time, distance -> Race(time, distance) }
    }

    private fun parseToRace(input: String): Race {
        val lines = input.trim().lines()

        val timeValue = lines[0].split("\\s+".toRegex()).drop(1).joinToString("").toLong()
        val distanceValue = lines[1].split("\\s+".toRegex()).drop(1).joinToString("").toLong()

        return Race(timeValue, distanceValue)
    }
}

data class Race(val time: Long, val distance: Long)

fun main() {
    val input = DataParser.parseString("day06.txt")

    println("Solution part one: " + Day06.partOne(input))
    println("Solution part two: " + Day06.partTwo(input))
}