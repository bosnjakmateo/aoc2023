import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day07Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseString("day07.txt")

        assertEquals(6440, Day07.partOne(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseString("day07.txt")

        assertEquals(5905, Day07.partTwo(input))
    }
}