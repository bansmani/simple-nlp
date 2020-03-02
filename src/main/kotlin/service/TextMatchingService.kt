package service

import textutilstests.DataSet
import textutilstests.OptionsMatching

class TextMatchingService(text: String, options: String) {

    private val dataSet = DataSet(text)
    private val optionsMatching = OptionsMatching(options)

    fun matchUsingOptions(question: String): String {
        val result = dataSet.findMatchingRows(question)
        return optionsMatching.matchOptions(result)
    }

}