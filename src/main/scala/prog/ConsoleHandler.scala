package prog

import scala.collection.mutable
import prog.Client._


object ConsoleHandler {
  val history = new mutable.Queue[String]
  def handler(input: String) {
    try {
      var line = input
      while (line.contains("  ")) line = line.trim.replaceAll("\\s\\s", " ")
      val command = line.split(" ", 2)
      command(0).trim match {
        case "help" => println(help)
        case "info" => println("\tType of collection is: " + collection.getClass + "\n\t" +
          "Initialization time is: " + start.toString + "\n\t" +
          "Collection size is: " + collection.size + "\n\t" +
          "Collection hash-code is: " + collection.hashCode())
        case "show" =>
          if (collection.isEmpty) println("\tCollection is empty")
          else collection.foreach(f => println(f))
        case "add" =>  // send to server
        //          collection.addOne(FlatReader.stringToFlat(command(1).trim))
        case "update" =>  // send to server
          //          val updateCommand = command(1).trim.split(" ", 2)
//          val id: Int = updateCommand(0).trim.toInt
//          val flat: Flat = FlatReader.stringToFlat(updateCommand(1).trim, id)
//          collection.update(collection.indexWhere(f => f.id_() == id), flat)
        case "remove_by_id" =>  // send to server
        //          collection.removeFirst(f => f.id_() == command(1).trim.toInt) match {
//          case Some(i) => println(s"\tDelete:\n$i")
//          case None => println("\tElement with such id doesn't exist") }
        case "clear" => // send to server
//          collection.clear()
//          println("\tCollection is cleared")
        case "execute_script" => // send to server
//          ReadFromFile.readCommands(command(1).trim)
        case "exit" => sys.exit()
        case "remove_head" =>  // send to server
        //          println("\tDelete:" + collection.remove(0))
        case "remove_greater" =>  // send to server
        //          println("\tDelete " + collection.removeAll(f => f.hashCode() > FlatReader.stringToFlat(command(1).trim).hashCode()).size + " elements")
        case "history" => history.foreach(line => println(s"\t$line"))
        case "remove_all_by_number_of_rooms" =>  // send to server
        //          println("\tDelete " + collection.removeAll(f => f.numberOfRooms_() == command(1).trim.toInt).size + " elements")
        case "count_by_number_of_rooms" => println("\t" + collection.count(f => f.numberOfRooms_() == command(1).trim.toInt))
        case "print_field_descending_view" =>
          if (collection.isEmpty) println("\tCollection is empty, can't show you anything")
          else collection.sortBy(-_.hashCode()).foreach(x => println("\t" + x.view_()))
        case _ => println("\tYou write wrong command, type 'help' to get list of commands")
      }
      history += command(0)
      if (history.size > 10) history.remove(0)
    } catch {
      case e : Throwable => Console.err.println("\tProblem execution the command:\n\t" + e.getMessage)
    }
  }

  private val help: String =
    """    help : вывести справку по доступным командам
      |    info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
      |    show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении
      |    add {element} : добавить новый элемент в коллекцию
      |    update id {element} : обновить значение элемента коллекции, id которого равен заданному
      |    remove_by_id id : удалить элемент из коллекции по его id
      |    clear : очистить коллекцию
      |    save : сохранить коллекцию в файл
      |    execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
      |    exit : завершить программу (без сохранения в файл)
      |    remove_head : вывести первый элемент коллекции и удалить его
      |    remove_greater {element} : удалить из коллекции все элементы, превышающие заданный (сравниваются хэш-коды)
      |    history : вывести последние 10 команд (без их аргументов)
      |    remove_all_by_number_of_rooms numberOfRooms : удалить из коллекции все элементы, значение поля numberOfRooms которого эквивалентно заданному
      |    count_by_number_of_rooms numberOfRooms : вывести количество элементов, значение поля numberOfRooms которых равно заданному
      |    print_field_descending_view : вывести значения поля view всех элементов в порядке убывания (убывания хэш-кодов)""".stripMargin
}
