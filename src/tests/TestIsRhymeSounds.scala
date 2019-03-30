package tests
import org.scalatest._
import rhymes.RhymingDictionary

class TestIsRhymeSounds extends FunSuite {
  test("Check If Words Rhyme"){
    val rhymePairs: Map[List[String], List[String]] = Map(
      List("S", "T", "AE1", "F") ->
        List("AO1", "T", "AH0", "G", "R", "AE2", "F"),
      List("THOUSAND", "TH", "AW1", "Z", "AH0", "N", "D") ->
        List("ACKLAND", "AE1", "K", "L", "AH0", "N", "D"),
      List("THOUSAND", "TH", "AW1", "Z", "AH0", "N", "D") ->
        List("ALMELUND", "AA0", "L", "M", "AH0", "L", "AH1", "N", "D"),
      List("SAMPLER", "S", "AE1", "M", "P", "L", "ER0") ->
        List("DOCTOR", "D", "AA1", "K", "T", "ER0")
    )
    val nonRhymePairs: Map[List[String], List[String]] = Map(
      List("WORLD", "W", "ER1", "L", "D") ->
        List("WORK", "W", "ER1", "K"),
      List("D", "AY1", "M", "AH0", "N", "D") ->
        List("B", "AY2", "OW0", "EH1", "TH", "IH0", "K", "S"),
      List() -> List(),
      List("") -> List(""),
      List() -> List("WORK", "W", "ER1", "K"),
      List("WORK", "W", "ER1", "K") -> List()
    )

    for ((pro1, pro2) <- rhymePairs){
      assert(RhymingDictionary.isRhymeSounds(pro1, pro2))
    }
    for ((pro1, pro2) <- nonRhymePairs){
      assert(!RhymingDictionary.isRhymeSounds(pro1, pro2))
    }
    val nothing: List[String] = List()
    assert(!RhymingDictionary.isRhymeSounds(nothing, nothing))

    val morePairs: Map[List[String], List[String]] = Map(
      List("Aw1") -> List("Aw0"),
      List("BEE", " ", "B", "IY1") -> List("GEE", " ", "JH", "IY1")
    )
    for ((pro1, pro2) <- morePairs){
      assert(RhymingDictionary.isRhymeSounds(pro1, pro2), pro1)
    }
  }
}
