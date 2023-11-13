package chapter5.ex10

import chapter3.List
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import chapter5.toList
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise10 : WordSpec({
    fun <A> Stream<A>.take(n: Int): Stream<A> =
        when (this) {
            is Empty -> this
            is Cons ->
                if (n == 0) Empty
                else Stream.cons(this.head) { this.tail().take(n - 1) }
        }

    //tag::init[]
    fun fibs(): Stream<Int> {
        fun fibsFunc(now: Int, next: Int): Stream<Int> {
            return Stream.cons({ now }, { fibsFunc(next, now + next) })
        }
        return fibsFunc(0, 1)
    }
    //end::init[]

    "!fibs" should {
        "return a Stream of fibonacci sequence numbers" {
            fibs().take(10).toList() shouldBe
                    List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
        }
    }
})
