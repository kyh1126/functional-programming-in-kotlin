package chapter5.ex9

import chapter3.List
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import chapter5.toList
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise9 : WordSpec({
    fun <A> Stream<A>.take(n: Int): Stream<A> =
        when (this) {
            is Empty -> this
            is Cons ->
                if (n == 0) Empty
                else Stream.cons(this.head) { this.tail().take(n - 1) }
        }

    //tag::init[]
    fun from(n: Int): Stream<Int> =
        Stream.cons({ n }, { from(n + 1) })
    //end::init[]

    "!from" should {
        "return a Stream of ever incrementing numbers" {
            from(5).take(5).toList() shouldBe
                    List.of(5, 6, 7, 8, 9)
        }
    }
})
