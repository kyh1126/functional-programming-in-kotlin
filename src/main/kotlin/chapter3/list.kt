package chapter3

sealed class List<out A> {
    companion object {
        fun <A> of(vararg aa: A): List<A> {
            val tail = aa.sliceArray(1 until aa.size)
            return if (aa.isEmpty()) Nil else Cons(aa[0], of(*tail))
        }

        fun <A> empty(): List<A> = Nil
    }

    fun <A, B> foldRight(xs: List<A>, z: B, f: (A, B) -> B): B =
        when (xs) {
            is Nil -> z
            is Cons -> f(xs.head, foldRight(xs.tail, z, f))
        }

    tailrec fun <A, B> foldLeft(xs: List<A>, z: B, f: (B, A) -> B): B =
        when (xs) {
            is Nil -> z
            is Cons -> foldLeft(xs.tail, f(z, xs.head), f)
        }

    fun reverse(): List<A> =
        foldLeft(this, empty(), { t: List<A>, h: A -> Cons(h, t) })
}

object Nil : List<Nothing>() {
    override fun toString(): String = "Nil"
}

data class Cons<out A>(val head: A, val tail: List<A>) : List<A>()
