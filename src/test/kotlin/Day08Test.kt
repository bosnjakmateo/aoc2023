import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day08Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseString("day08a.txt")

        assertEquals(6, Day08.partOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseString("day08b.txt")

        assertEquals(6, Day08.partTwo(input))
    }
}