package chapter5.ex1

import chapter3.List
import chapter3.reverse
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise1 : WordSpec({
    //tag::init[]
    fun <A> Stream<A>.toList(): List<A> {
        tailrec fun <A> makeList(target: Stream<A>, tail: List<A>): List<A> =
            when (target) {
                is Empty -> tail
                is Cons -> makeList(target.tail(), chapter3.Cons(target.head(), tail))
            }
        return reverse(makeList(this, List.empty()))
    }

    //end::init[]

    "!Stream.toList" should {
        "force the stream into an evaluated list" {
            val s = Stream.of(1, 2, 3, 4, 5)
            s.toList() shouldBe List.of(1, 2, 3, 4, 5)
        }
    }
})
