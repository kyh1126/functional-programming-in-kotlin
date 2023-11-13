package chapter5.ex8

import chapter3.List
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import chapter5.toList
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise8 : WordSpec({
    fun <A> Stream<A>.take(n: Int): Stream<A> =
        when (this) {
            is Empty -> this
            is Cons ->
                if (n == 0) Empty
                else Stream.cons(this.head) { this.tail().take(n - 1) }
        }

    //tag::init[]
    fun <A> constant(a: A): Stream<A> =
        Stream.cons({ a }, { constant(a) })
    //end::init[]

    "!constants" should {
        "return an infinite stream of a given value" {
            constant(1).take(5).toList() shouldBe
                    List.of(1, 1, 1, 1, 1)
        }
    }
})
