import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseStrings("day03.txt")

        assertEquals(4361, day03PartOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseStrings("day03.txt")

        assertEquals(467835, day03PartTwo(input))
    }
}