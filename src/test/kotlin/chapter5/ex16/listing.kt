package chapter5.ex16

import chapter3.List
import chapter5.Stream
import chapter5.toList
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise16 : WordSpec({

    //tag::scanright[]
    fun <A, B> Stream<A>.scanRight(z: B, f: (A, () -> B) -> B): Stream<B> =
        foldRight({ z to Stream.of(z) },
            { a: A, b: () -> Pair<B, Stream<B>> ->
                val head: Pair<B, Stream<B>> by lazy { b() }
                val tail: B = f(a) { head.first }
                Pair(tail, Stream.cons({ tail }, { head.second }))
            }).second
    //end::scanright[]

    "!Stream.scanRight" should {
        "behave like foldRight" {
            Stream.of(1, 2, 3)
                .scanRight(0, { a, b ->
                    a + b()
                }).toList() shouldBe List.of(6, 5, 3, 0)
        }
    }
})
