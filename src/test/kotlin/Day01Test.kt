import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class Day01Test {

    @Test
    fun `should calculate part one`() {
        val input = DataParser.parseStrings("day01a.txt")

        assertEquals(142, Day01.solution(input))
    }

    @Test
    fun `should calculate part two`() {
        val input = DataParser.parseStrings("day01b.txt")

        assertEquals(281, Day01.solution(input))
    }
}