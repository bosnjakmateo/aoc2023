import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day09Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseString("day09.txt")

        assertEquals(114, Day09.partOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseString("day09.txt")

        assertEquals(2, Day09.partTwo(input))
    }
}