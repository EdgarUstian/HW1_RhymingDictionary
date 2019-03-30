package rhymes
import scala.io.{BufferedSource, Source}
import scala.util.control.Breaks._

object RhymingDictionary {
  def isRhymeSounds(word1: List[String], word2: List[String]): Boolean = {
    var theShortOne: Int = 0
    var rhyme: Boolean = true
    breakable {
      if ((word1.isEmpty || word2.isEmpty) || (word1 == List("")||(word2 == List("")))) {
        rhyme = false
        break
      }else{
        if(word1.length > word2.length){
          theShortOne = word2.length
        }else{
          theShortOne = word1.length
        }
        for(i <- 0 to theShortOne){
          if(word1.reverse(i).length == 3 && word2.reverse(i).length == 3 &&
            word1.reverse(i).dropRight(1).toUpperCase() ==
              word2.reverse(i).dropRight(1).toUpperCase()){
            rhyme = true
            break
          }else if(word1.reverse(i).toUpperCase() ==
            word2.reverse(i).toUpperCase()){
          }else{
            rhyme = false
            break
          }
        }
      }
    }
    rhyme
  }
  //finds a word inside a dictionary and returns pronunciation only
  //and returns it as a List[String]
  def getSounds(filename: String, word: String): List[String] ={
    val file: BufferedSource = scala.io.Source.fromFile(filename)
    var pronunciation: List[String] = List()
    breakable{
      if(word == ""){
        break()
      }
      else {
        for (line <- file.getLines()) {
          if (line.startsWith(word.toUpperCase())) {
            pronunciation = line.replaceFirst(
              word.toUpperCase() + "  ", "")
              .split(" ").toList
            break
          }
        }
      }
    }
    pronunciation
  }


  def isRhyme(filename: String, word1: String, word2: String): Boolean = {
    val pronunciation1: List[String] = getSounds(filename, word1)
    val pronunciation2: List[String] = getSounds(filename, word2)
    if (pronunciation1.isEmpty||pronunciation2.isEmpty){
      false
    }
    else {
      if ((word1 == "")&&(word2 == "")) {
        true
      }
      else isRhymeSounds(pronunciation1, pronunciation2)
    }
  }


  def findRhymes(filename: String, masterWord: String): List[String] = {
    val emptyList: List[String] =List()
    def getWordAndPronounce(line: String): List[String] = {
      val firstWord: String = line.split("  ")(0)
      val pronunciation: List[String] =
        line.split("  ")(1).split(" ").toList
      val wholePronunciation: List[String] =
        List.concat(List(firstWord), pronunciation)
      wholePronunciation
    }
    val masterWordPronunciation: List[String] = getSounds(filename, masterWord)
    var rhymeList: List[String] = List()
    for (line <- Source.fromFile(filename).getLines().drop(56)){
      if (isRhymeSounds(masterWordPronunciation, getWordAndPronounce(line))){
        rhymeList = rhymeList :+ getWordAndPronounce(line).head
      }
      else {
        emptyList
      }
    }
    rhymeList
  }
}
