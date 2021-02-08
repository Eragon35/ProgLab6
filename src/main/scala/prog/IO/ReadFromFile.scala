package prog.IO

import prog.Main.collection
import prog.Model.{FlatReader, IdGenerator}

import java.io.{BufferedInputStream, File, FileInputStream}
import scala.xml
import scala.xml.NodeSeq.Empty.text
import scala.xml.XML

object ReadFromFile {
  def readXml(fileName : String) = {
//    val inputStream = new BufferedInputStream(new FileInputStream(fileName)
    val xml = XML.loadFile(new File(fileName))
    var maxId = -1
    for (flat <- xml \\ "file" \\ "flat") {
      collection.addOne(FlatReader.fromXml(flat))
      if ((flat \ "id").text.toInt > maxId) maxId = (flat \ "id").text.toInt
    }
    IdGenerator.setId(maxId)
  }

  def readCommands (fileName: String) = {}

}
