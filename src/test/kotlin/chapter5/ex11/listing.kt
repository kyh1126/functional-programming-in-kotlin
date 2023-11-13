package chapter5.ex11

import chapter3.List
import chapter4.Option
import chapter4.Some
import chapter4.getOrElse
import chapter5.Cons
import chapter5.Empty
import chapter5.Stream
import chapter5.toList
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

fun <A> Stream<A>.take(n: Int): Stream<A> =
    when (this) {
        is Empty -> this
        is Cons ->
            if (n == 0) Empty
            else Stream.cons(this.head) { this.tail().take(n - 1) }
    }

//tag::init[]
fun <A, S> unfold(z: S, f: (S) -> Option<Pair<A, S>>): Stream<A> =
    f(z).map { pair ->
        Stream.cons({ pair.first }, { unfold(pair.second, f) })
    }.getOrElse { Empty }
//end::init[]

//TODO: Enable tests by removing `!` prefix
class Exercise11 : WordSpec({
    "!unfold" should {
        """return a stream based on an initial state and a function
            applied to each subsequent element""" {
            unfold(0, { s: Int ->
                Some(s to (s + 1))
            }).take(5).toList() shouldBe
                    List.of(0, 1, 2, 3, 4)
        }
    }
})
