import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day05Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseStrings("day05.txt")

        assertEquals(35, Day05.partOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseStrings("day05.txt")

        assertEquals(46, Day05.partTwo(input))
    }
}