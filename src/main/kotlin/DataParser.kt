import java.io.File

object DataParser {

    fun parseStrings(fileName: String) =
        File(DataParser::class.java.getResource("/${fileName}")!!.toURI()).readLines()

    fun parseInts(fileName: String) = parseStrings(fileName).map { it.toInt() }

    fun parseString(fileName: String): String =
        File(DataParser::class.java.getResource("/${fileName}")!!.toURI()).readText()
}