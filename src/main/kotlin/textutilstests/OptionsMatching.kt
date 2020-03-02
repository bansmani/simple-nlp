package textutilstests

import java.util.*

class OptionsMatching(options: String) {

    private val optionsDataSet = DataSet(options)
    fun matchOptions(matchingResults: TreeSet<MatchingRow>): String {

        val matchingRows: List<MatchingRow> = matchingResults.map {
            optionsDataSet.findMatchingRows(it.rawdata)
        }.flatten().distinctBy { row -> row.rawdata }

        if (matchingRows.size == 1) {
            return matchingRows[0].rawdata
        } else if (matchingRows.size > 1) {
            //searching for best match
            return matchingRows.filter { it.wordList.size == it.wordMatchCount }[0].rawdata
        }
        return ""
    }

}