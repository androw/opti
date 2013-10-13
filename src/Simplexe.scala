/**
 * Created with IntelliJ IDEA.
 * User: Nicolas Lorin
 * Date: 27/02/13
 */
class Simplexe(val simplexe: Array[Array[Double]], val base: Array[Array[Int]], val interest: Int, val pena: Boolean) {
  def isOptimal: Boolean = {
    for (i <- 0 until interest) {
      println(simplexe(0)(i))
      if (simplexe(0)(i) < -0.00001) { //SALLLLLLLL
        return false
      }

    }
    return true
  }

  def entrant = {
    var min = 0.0
    var minIndex = 0
    for (i <- 0 until simplexe(0).length-2) {
      if (simplexe(0)(i) < min) {
        minIndex = i
        min = simplexe(0)(i)
      } else if (simplexe(0)(i) == min && pena) {
        if (simplexe(1)(minIndex) > simplexe(1)(i)) {
          minIndex = i
          min = simplexe(0)(i)
        }
      }
    }
    minIndex
  }
  def sortant(entrant: Int) = {
    var min = 100000.0
    var minIndex = Array(base(0)(0),base(0)(1))
    for (i <- 0 until base.length) {
      val valeur = (simplexe(base(i)(0))(simplexe(base(i)(0)).length-1))/(simplexe(base(i)(0))(entrant))
      if (0 < valeur && valeur < min) {
        minIndex = Array(base(i)(0),base(i)(1))
        min = valeur
      }
    }
    minIndex
  }
  def update(entrant: Int, sortant: Int) {
    val pivot = simplexe(sortant)(entrant)
    for (i <- 0 until simplexe(sortant).length) {
      simplexe(sortant)(i) = simplexe(sortant)(i)/pivot
    }
    for (i <- 0 until simplexe.length if (i != sortant) ) {
      val pivot = simplexe(i)(entrant)
      for (j <- 0 until simplexe(i).length) {
        simplexe(i)(j) = simplexe(i)(j) - pivot*simplexe(sortant)(j)
      }
    }
  }
  def updateBase(entrant: Int, sortant: Array[Int]) {
    val rem = base.indexWhere((p: Array[Int]) => (p(0) == sortant(0) && p(1) == sortant(1)))
    base(rem) = Array(sortant(0),entrant)
  }
  def optimize = {
    while (!isOptimal) {
      val varEntrant = entrant
      val varSortant = sortant(varEntrant)
      println("Base non optimale")
      println("Entrant : "+ varEntrant)
      println("Sortant : "+ varSortant(1))
      println()

      update(varEntrant, varSortant(0))
      updateBase(varEntrant, varSortant)

    }
    println("Base optimale")
    println(simplexe(0)(simplexe(0).length-1)*simplexe(0)(simplexe(0).length-2))
    if (pena) println(simplexe(1)(simplexe(1).length-1)*simplexe(1)(simplexe(1).length-2))
    for (i <- 0 until base.length) {
      println("Var "+base(i)(1) +" = "+simplexe(base(i)(0))(simplexe(base(i)(0)).length-1))
    }
  }
}
