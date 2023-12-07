object Day05 {
    fun partOne(input: List<String>): Long {
        val almanac = parseAlmanac(input)
        var minLocation = Long.MAX_VALUE

        for (seed in almanac.seeds) {
            val location = getLocation(seed, almanac)

            if (location < minLocation) {
                minLocation = location
            }
        }

        return minLocation
    }

    fun partTwo(input: List<String>): Long {
        val almanac = parseAlmanac(input)
        var minLocation = Long.MAX_VALUE

        var i = 0

        while (i < almanac.seeds.size - 2) {
            val startRange = almanac.seeds[i]
            val endRange = almanac.seeds[i + 1]
            var seed = startRange

            while (seed < endRange + startRange) {
                val location = getLocation(seed, almanac)

                if (location < minLocation) {
                    minLocation = location
                }

                seed++
            }
            i += 2
        }

        return minLocation
    }

    private fun getLocation(seed: Long, almanac: Almanac): Long {
        var currentSeed = seed

        for (map in almanac.maps) {
            currentSeed = calculateNewSeeds(currentSeed, map)
        }
        return currentSeed
    }

    private fun calculateNewSeeds(seed: Long, maps: List<AlmanacMap>): Long {
        return maps.firstNotNullOfOrNull {
            if (seed >= it.source && seed <= it.source + it.length) {
                seed + (it.destination - it.source)
            } else {
                null
            }
        } ?: seed
    }

    private fun parseAlmanac(input: List<String>): Almanac {
        val seeds = input[0].substringAfter(":").trim().split(" ").map { it.toLong() }

        val maps = mutableListOf<List<AlmanacMap>>()
        var currentMaps = mutableListOf<AlmanacMap>()

        for (line in input.subList(1, input.size)) {
            when {
                line.isBlank() -> continue
                line.endsWith(" map:") -> {
                    currentMaps = mutableListOf()
                    maps.add(currentMaps)
                }

                else -> {
                    val values = line.trim().split(" ").map { it.toLong() }
                    currentMaps.add(AlmanacMap(values[0], values[1], values[2]))
                }
            }
        }

        return Almanac(seeds, maps)
    }
}

data class Almanac(var seeds: List<Long>, var maps: List<List<AlmanacMap>>)
data class AlmanacMap(val destination: Long, val source: Long, val length: Long)

fun main() {
    val input = DataParser.parseStrings("day05.txt")

    println("Solution part one: " + Day05.partOne(input))
    println("Solution part two: " + Day05.partTwo(input))
}