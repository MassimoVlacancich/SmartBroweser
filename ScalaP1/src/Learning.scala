/**
  * Created by HP on 31/05/2017.
  */
class Learning {
  /* This is my first java program.
     * This will print 'Hello World' as the output
     */

  def addInt(a:Int, b:Int) : Int ={
    return a + b;
  }

  def noReturn () : Unit = {
    println("this statement doesn t return anything");
  }

  def time() = {
    println("Getting time in nano seconds")
    System.nanoTime
  }
  def delayed( t: => Long ) = {
    println("In delayed method")
    println("Param: " + t)
  }

  //could pass just one string or more tha one and it would still work
  def printStrings( args:String* ) = {
    var i : Int = 0;

    for( arg <- args ){
      println("Arg value[" + i + "] = " + arg );
      i = i + 1;
    }
  }

  //Funtcion literals
  var inc = (x:Int) => x+1
  var mul = (x: Int, y: Int) => x*y
  //Variable inc is now a function that can be used the usual way −


  //Currying
  //Currying transforms a function that takes multiple parameters into a chain of functions, each taking a single parameter.
  def strcat(s1: String)(s2: String) = s1 + s2
  def strcat(s1: String) = (s2: String) => s1 + s2


  //closure
  //A closure is a function, whose return value depends on the value of one or more variables declared outside this function.
  var factor = 3
  val multiplier = (i:Int) => i * factor
  def closure (i: Int) : Int = {
    return i*factor
  }
}
