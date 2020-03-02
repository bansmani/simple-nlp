package textutilstests

import java.util.*
import java.util.regex.Pattern


data class DataRow(val rawdata: String, val wordList: List<String>)
data class MatchingRow(
    val rawdata: String,
    val wordMatchCount: Int,
    val matchingWords: List<String>,
    val wordList: List<String>
)

class DataSet(text: String) {
    private val rows = mutableListOf<DataRow>()

    init {
        text.replace(";", ".")
            .split(".").map { line ->
                val row = DataRow(
                    line.replace(Regex("\\r?\\n"), " ").trim(),
                    line.replace(",", " ")
                        .trim()
                        .split(Pattern.compile("\\s+"))
                        .filter { !stopwords.contains(it.toLowerCase().trim()) }
                        .distinct()
                        .toList()
                )
                if (row.rawdata.trim() != "")
                    rows.add(row)
            }


    }

    fun findMatchingRows(query: String): TreeSet<MatchingRow> {
        val queryWords = clearPeriodChar(clearStopWords(query)).split(Pattern.compile("\\s+"))
        var maxMatchCount = 0 //keep only best matching results based on the count.

        val matchingRows = TreeSet<MatchingRow>(
            Comparator { o1: MatchingRow, o2: MatchingRow ->
                val res = (100 * o2.wordMatchCount / o2.wordList.size) - (100 * o1.wordMatchCount / o1.wordList.size)
                if (res == 0) 1 else res
            })

        rows.forEach { row ->
            var matchcount = 0
            val matchingWords = mutableListOf<String>()
            queryWords.distinct().forEach { word ->
                row.wordList.forEach {

                    val left = it.toLowerCase()
                    val right = word.toLowerCase()

                    if (left == right) {
                        matchingWords.add(left)
                        matchcount++
                    } else if (left == right + "s" || left == right + "es" || left == right + "ies") {
                        matchingWords.add(right)
                        matchcount++
                    } else if (right == left + "s" || right == left + "es" || right == left + "ies") {
                        matchingWords.add(left)
                        matchcount++
                    }
                }

            }
            if (matchcount > maxMatchCount)
                maxMatchCount = matchcount

            if (matchcount > 0 && matchcount >= maxMatchCount)
                matchingRows.add(MatchingRow(row.rawdata, matchcount, matchingWords, row.wordList))
        }
        matchingRows.removeIf {
            it.rawdata == ""
        }
        return matchingRows

    }
}