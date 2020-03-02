package textutilstests

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

class TextAnalyserTest {

    @Test
    fun `find matching text using given input`() {
        val text = "world is great. India is a wonderful country"
        val dataSet = DataSet(text)

        val question = "which country is wonderful"
        Assertions.assertEquals("India is a wonderful country", dataSet.findMatchingRows(question).first().rawdata)

    }

    @Test
    fun `result should to sorted by match count when multiple matching answers`() {

        val text = "entire world is beautiful." +
                "I love India, it is a great country. " +
                "Russia is a big country.  " +
                "India is a wonderful country."

        val textAnalyzer = DataSet(text)

        val question = "which country is wonderful"
        Assertions.assertEquals("India is a wonderful country", textAnalyzer.findMatchingRows(question).first().rawdata)

    }
}