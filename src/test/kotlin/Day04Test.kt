import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day04Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseStrings("day04.txt")

        assertEquals(13, day04PartOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseStrings("day04.txt")

        assertEquals(30, day04PartTwo(input))
    }
}