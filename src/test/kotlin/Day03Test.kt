import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day03Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseStrings("day03.txt")

        assertEquals(4361, Day03.partOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseStrings("day03.txt")

        assertEquals(467835, Day03.partTwo(input))
    }
}