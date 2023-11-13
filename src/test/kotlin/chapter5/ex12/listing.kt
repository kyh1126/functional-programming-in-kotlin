package chapter5.ex12

import chapter3.List
import chapter4.Some
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import chapter5.toList
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import utils.SOLUTION_HERE

//TODO: Enable tests by removing `!` prefix
class Exercise12 : WordSpec({
    fun <A> Stream<A>.take(n: Int): Stream<A> =
        when (this) {
            is Empty -> this
            is Cons ->
                if (n == 0) Empty
                else Stream.cons(this.head) { this.tail().take(n - 1) }
        }

    //tag::fibs[]
    fun fibs(): Stream<Int> =
        Stream.unfold(0 to 1) { (now, next) -> Some(now to (next to now + next)) }
    //end::fibs[]

    //tag::from[]
    fun from(n: Int): Stream<Int> =
        Stream.unfold(n) { z -> Some(z to z + 1) }
    //end::from[]

    //tag::constant[]
    fun <A> constant(n: A): Stream<A> =
        Stream.unfold(n) { z -> Some(z to z) }
    //end::constant[]

    //tag::ones[]
    fun ones(): Stream<Int> =
        Stream.unfold(1) { z -> Some(z to z) }
    //end::ones[]

    "!fibs" should {
        "return a Stream of fibonacci sequence numbers" {
            fibs().take(10).toList() shouldBe
                    List.of(0, 1, 1, 2, 3, 5, 8, 13, 21, 34)
        }
    }

    "!from" should {
        "return a Stream of ever incrementing numbers" {
            from(5).take(5).toList() shouldBe List.of(5, 6, 7, 8, 9)
        }
    }

    "!constants" should {
        "return an infinite stream of a given value" {
            constant(1).take(5).toList() shouldBe
                    List.of(1, 1, 1, 1, 1)
        }
    }

    "!ones" should {
        "return an infinite stream of 1s" {
            ones().take(5).toList() shouldBe List.of(1, 1, 1, 1, 1)
        }
    }
})
