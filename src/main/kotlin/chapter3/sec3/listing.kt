package chapter3.sec3

//tag::init1[]
sealed class List<out A>
object Nil : List<Nothing>() {
    override fun toString(): String = "Nil"
}
data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()

fun <A> append(a1: List<A>, a2: List<A>): List<A> =
    when (a1) {
        is Nil -> a2
        is Cons -> Cons(a1.head, append(a1.tail, a2))
    }
//end::init1[]
