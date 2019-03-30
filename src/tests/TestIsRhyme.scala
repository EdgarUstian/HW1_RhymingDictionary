package tests
import org.scalatest._
import rhymes.RhymingDictionary

class TestIsRhyme extends FunSuite {
  test("Check isRhyme"){
    val filename: String = "data/cmudict-0.7b.txt"
    val wordPairsYes: Map[String, String] = Map(
      "DATA" -> "BETA",
      "STAFF" -> "PHOTOGRAPH",
      "HALF" -> "LAUGH",
      "DEGENERES" -> "DEGENERES(1)",
      "ENTERTAIN" -> "ENTERTAIN(1)",
    )
    val wordPairsNo: Map[String, String] = Map(
      "DATA" -> "SIMPLE",
      "STAFF" -> "SIMPLE",
      "HALF" -> "SIMPLE",
      "DEGENERATE" -> "DEGENERATE(1)",
      "DEEHAN" -> "DEEHAN(1)",
      "A" -> "A(1)",
      "" -> "",
      " " -> ""
    )

    for ((word1, word2) <- wordPairsYes){
      assert(RhymingDictionary.isRhyme(filename, word1, word2), word1)
    }
    for ((word1, word2) <- wordPairsNo){
      assert(!RhymingDictionary.isRhyme(filename, word1, word2))
    }
    assert(!RhymingDictionary.isRhyme("data/cmudict-0.7b.txt", "", ""))
  }
}