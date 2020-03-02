package textutilstests

val stopwords: List<String> =
    ClassLoader.getSystemResourceAsStream("stopwords.txt")?.reader()?.readLines()?.map { it } ?: emptyList()

fun clearStopWords(text: String) : String {
    return text.split(" ")
        .filter { !stopwords.contains(it.toLowerCase().trim()) }
        .joinToString(" ")
}
fun clearPeriodChar(text: String) : String {
    return text.replace(".", " ")
        .replace(";" , " ")
        .replace("?", " ")
        .replace(",", " ")
}
