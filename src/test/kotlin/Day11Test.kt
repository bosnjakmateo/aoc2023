import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseStrings("day11.txt")

        assertEquals(374, Day11.partOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseStrings("day11.txt")

        assertEquals(1030, Day11.partOne(input, 10 - 1))
    }
}