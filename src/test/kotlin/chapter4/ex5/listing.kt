package chapter4.ex5

import chapter3.Cons
import chapter3.List
import chapter3.foldRight
import chapter4.None
import chapter4.Option
import chapter4.Some
import chapter4.map2
import io.kotest.core.spec.style.WordSpec
import io.kotest.matchers.shouldBe

//TODO: Enable tests by removing `!` prefix
class Exercise5 : WordSpec({

    //tag::traverse[]
    fun <A, B> traverse(
        xa: List<A>,
        f: (A) -> Option<B>
    ): Option<List<B>> =
        foldRight(xa, Some(List.empty())) { a: A, optionB: Option<List<B>> ->
            map2(f(a), optionB) { b: B, listB: List<B> -> Cons(b, listB) }
        }
    //end::traverse[]

    fun <A> sequence(xs: List<Option<A>>): Option<List<A>> =
        traverse(xs) { it }

    fun <A> catches(a: () -> A): Option<A> =
        try {
            Some(a())
        } catch (e: Throwable) {
            None
        }

    "!traverse" should {
        """return some option of a transformed list if all
            transformations succeed""" {
            val xa = List.of(1, 2, 3, 4, 5)
            traverse(xa) { a: Int ->
                catches { a.toString() }
            } shouldBe Some(
                List.of("1", "2", "3", "4", "5")
            )
        }

        "return a none option if any transformations fail" {
            val xa = List.of("1", "2", "x", "4")
            traverse(xa) { a ->
                catches { a.toInt() }
            } shouldBe None
        }
    }

    "!sequence" should {
        "turn a list of some options into an option of list" {
            val lo =
                List.of(Some(10), Some(20), Some(30))
            sequence(lo) shouldBe Some(List.of(10, 20, 30))
        }

        "turn a list of options containing a none into a none" {
            val lo =
                List.of(Some(10), None, Some(30))
            sequence(lo) shouldBe None
        }
    }
})
