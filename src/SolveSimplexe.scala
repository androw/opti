/**
 * Created with IntelliJ IDEA.
 * User: Nicolas Lorin
 * Date: 26/02/13
 */
object SolveSimplexe {
  def main(args: Array[String]) {
    //println("Hello,  world!")
    val simp = Array(Array(-80, -160, -200, 0.0, 0, 1, 0), Array(1, 3,5 ,1 ,0.0 ,0,200), Array(2,2,2,0.0,1,0,200))
    var base = Array(Array(1,3),Array(2,4))
    val simplexe = new Simplexe(simp, base, 3, false)

    //simplexe.optimize

    val simp2 = Array(Array(-2.0,0,-8,1,1,0,0,-1,-9), Array(5.0,0,21,0,0,0,0,-1,0), Array(1.0,-1,6,-1,0,1,0,0,8), Array(1.0,1,2,0,-1,0,1,0,1))
    var base2 = Array(Array(2,5),Array(3,6))
    val simplexe2 = new Simplexe(simp2, base2, 5, true)

    simplexe2.optimize
  }
}