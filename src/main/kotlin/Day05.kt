object Day05 {
    fun partOne(input: List<String>): Long {
        val almanac = parseAlmanac(input)

        return almanac.seeds
            .map { SeedRange(it, it) }
            .minOf { getLocation(it, almanac) }
    }

    fun partTwo(input: List<String>): Long {
        val almanac = parseAlmanac(input)

        return almanac.seeds
            .windowed(2, 2)
            .map { SeedRange(it.first(), it.first() + it.last() - 1) }
            .minOf { getLocation(it, almanac) }
    }

    private fun getLocation(seedRange: SeedRange, almanac: Almanac): Long {
        var currentSeeds = listOf(seedRange)

        for (map in almanac.maps) {
            currentSeeds = calculateNewSeeds(currentSeeds, map)
        }
        return currentSeeds.flatMap { listOf(it.start, it.end) }.min()
    }

    private fun calculateNewSeeds(seedRanges: List<SeedRange>, maps: List<AlmanacMap>): List<SeedRange> {
        val oldSeeds = seedRanges.toMutableList()
        val newSeedRanges = mutableListOf<SeedRange>()

        for (map in maps) {
            if (oldSeeds.isEmpty()) break

            for (seed in oldSeeds.toList()) {
                oldSeeds.remove(seed)

                val (notMapped, mapped) = mapSeedRange(seed, map)

                newSeedRanges.addAll(mapped)
                oldSeeds.addAll(notMapped)
            }
        }

        return newSeedRanges.plus(oldSeeds)
    }

    private fun mapSeedRange(currentSeedRange: SeedRange, map: AlmanacMap): Pair<List<SeedRange>, List<SeedRange>> {
        val almanacMapSeedRange = SeedRange(map.source, map.source + map.length - 1)

        val overlapStart = maxOf(currentSeedRange.start, almanacMapSeedRange.start)
        val overlapEnd = minOf(currentSeedRange.end, almanacMapSeedRange.end)

        if (currentSeedRange.end < almanacMapSeedRange.start || currentSeedRange.start > almanacMapSeedRange.end) {
            return Pair(listOf(currentSeedRange), emptyList())
        }

        val overlappedSeedRange = SeedRange(
            overlapStart + (map.destination - map.source),
            overlapEnd + (map.destination - map.source)
        )

        if (currentSeedRange.start < almanacMapSeedRange.start && currentSeedRange.end > almanacMapSeedRange.end) {
            val start = SeedRange(currentSeedRange.start, almanacMapSeedRange.start - 1)
            val end = SeedRange(almanacMapSeedRange.end + 1, currentSeedRange.end)

            return Pair(listOf(start, end), listOf(overlappedSeedRange))
        }

        if (currentSeedRange.start < almanacMapSeedRange.start) {
            val start = SeedRange(currentSeedRange.start, almanacMapSeedRange.start - 1)
            return Pair(listOf(start), listOf(overlappedSeedRange))
        }

        if (currentSeedRange.end > almanacMapSeedRange.end) {
            val end = SeedRange(almanacMapSeedRange.end + 1, currentSeedRange.end)
            return Pair(listOf(end), listOf(overlappedSeedRange))
        }

        return Pair(emptyList(), listOf(overlappedSeedRange))
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
data class SeedRange(val start: Long, val end: Long)

fun main() {
    val input = DataParser.parseStrings("day05.txt")

    println("Solution part one: " + Day05.partOne(input))
    println("Solution part two: " + Day05.partTwo(input))
}