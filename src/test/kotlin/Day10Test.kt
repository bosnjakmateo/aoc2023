import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseStrings("day10a.txt")

        assertEquals(8, Day10.partOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseStrings("day10b.txt")

        assertEquals(10, Day10.partTwo(input))
    }
}