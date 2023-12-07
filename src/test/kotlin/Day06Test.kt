import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day06Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseString("day06.txt")

        assertEquals(288, Day06.partOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseString("day06.txt")

        assertEquals(71503, Day06.partTwo(input))
    }
}