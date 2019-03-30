package tests
import rhymes.RhymingDictionary
import org.scalatest._

class TestGetSounds extends FunSuite{
  test("Test Getting Sounds from file"){
    val filename: String = "data/cmudict-0.7b.txt"
    val realWords: Map[String, List[String]] = Map(
      "STAFF" -> List("S", "T", "AE1", "F"),
      "PHOTOGRAPH" -> List("F", "OW1", "T", "AH0", "G", "R", "AE2", "F"),
      "DIAMOND" -> List("D", "AY1", "M", "AH0", "N", "D"),
      "BIOETHICS" -> List("B", "AY2", "OW0", "EH1", "TH", "IH0", "K", "S")
    )
    for ((word, pronunciation) <- realWords){
      assert(RhymingDictionary.getSounds(filename, word) == pronunciation, word)
    }
    val fakeWords: List[String] = List("nahgay", "excusemesir", "wekkkkkkhopudt", "", "       ", "helphshdhdh")
    for (nonword <- fakeWords){
      assert(RhymingDictionary.getSounds(filename, nonword) == List(), nonword)
      println(RhymingDictionary.getSounds(filename, nonword))
    }
    val lowecase: Map[String, List[String]] = Map(
      "staff" -> List("S", "T", "AE1", "F"),
      "photograph" -> List("F", "OW1", "T", "AH0", "G", "R", "AE2", "F"),
      "diamond" -> List("D", "AY1", "M", "AH0", "N", "D"),
      "bioethics" -> List("B", "AY2", "OW0", "EH1", "TH", "IH0", "K", "S")
    )
    for ((word, pronounce) <- lowecase){
      assert(RhymingDictionary.getSounds(filename, word) == pronounce, word)
      println(RhymingDictionary.getSounds(filename, word))
    }
  }
}
