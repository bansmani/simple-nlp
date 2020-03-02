import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import service.TextMatchingService
import java.io.File

class TextMatchingServiceTest {


    val text = File("testdata/input.txt").readText()
    val options = File("testdata/options.txt").readText()
    val textMatchingService = TextMatchingService(text, options)


    @Test
    fun `test full wiki question`() {
        val lines: List<String> = File("testdata/Wiki.txt").readLines()

        Assertions.assertEquals(lines.get(7), textMatchingService.matchUsingOptions(lines.get(1)))
        Assertions.assertEquals(lines.get(8), textMatchingService.matchUsingOptions(lines.get(2)))
        Assertions.assertEquals(lines.get(9), textMatchingService.matchUsingOptions(lines.get(3)))
        Assertions.assertEquals(lines.get(10), textMatchingService.matchUsingOptions(lines.get(4)))
        Assertions.assertEquals(lines.get(11), textMatchingService.matchUsingOptions(lines.get(5)))

    }
}