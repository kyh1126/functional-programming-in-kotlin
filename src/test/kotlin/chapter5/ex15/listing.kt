package chapter5.ex15

import chapter3.List
import chapter4.None
import chapter4.Some
import chapter4.foldRight
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import chapter5.toList
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe
import chapter3.Cons as ConsL
import chapter3.Nil as NilL

//TODO: Enable tests by removing `!` prefix
class Exercise15 : WordSpec({

    //tag::tails[]
    fun <A> Stream<A>.tails(): Stream<Stream<A>> =
        Stream.unfold(this) { s: Stream<A> ->
            when (s) {
                is Empty -> None
                is Cons -> Some(s to s.tail())
            }
        }
    //end::tails[]

    fun <A, B> List<A>.map(f: (A) -> B): List<B> =
        this.foldRight(List.empty()) { a, b -> ConsL(f(a), b) }

    "!Stream.tails" should {
        "return the stream of suffixes of the input sequence" {
            Stream.of(1, 2, 3)
                .tails()
                .toList()
                .map { it.toList() } shouldBe
                    List.of(
                        ConsL(1, ConsL(2, ConsL(3, NilL))),
                        ConsL(2, ConsL(3, NilL)),
                        ConsL(3, NilL)
                    )
        }
    }
})
