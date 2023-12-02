import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day02Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseStrings("day02.txt")

        assertEquals(8, day02PartOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseStrings("day02.txt")

        assertEquals(2286, day02PartTwo(input))
    }
}