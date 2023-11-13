package chapter5.sec3

import chapter3.List
import chapter4.Option

//tag::imports[]
import chapter3.Cons as ConsL
import chapter3.Nil as NilL

//end::imports[]

fun <A, B> Stream<A>.map(f: (A) -> B): Stream<B> = TODO()

fun <A> Stream<A>.filter(f: (A) -> Boolean): Stream<A> = TODO()

fun <A> Stream<A>.toList(): List<A> = TODO()

fun <A> Stream<A>.headOption(): Option<A> = TODO()

sealed class Stream<out A> {

    companion object {
        fun <A> cons(hd: () -> A, tl: () -> Stream<A>): Stream<A> {
            val head: A by lazy(hd)
            val tail: Stream<A> by lazy(tl)
            return Cons({ head }, { tail })
        }

        fun <A> of(vararg xs: A): Stream<A> =
            if (xs.isEmpty()) empty()
            else cons({ xs[0] },
                { of(*xs.sliceArray(1 until xs.size)) })

        fun <A> empty(): Stream<A> = Empty
    }

    //tag::init1[]
    fun exists(p: (A) -> Boolean): Boolean =
        when (this) {
            is Cons -> p(this.head()) || this.tail().exists(p)
            else -> false
        }
    //end::init1[]

    //tag::init2[]
    fun <B> foldRight(
        z: () -> B,
        f: (A, () -> B) -> B // () -> B 타입은 f가 두 번째 인자로 받는 값이 이름에 의한 파라미터이며 평가되지 않을 수도 있음을 명시함
    ): B =
        when (this) {
            is Cons -> f(this.head()) {
                tail().foldRight(z, f) // f가 두 번째 인자를 평가하지 않는 경우 재귀가 결코 일어나지 않음
            }

            is Empty -> z()
        }
    //end::init2[]

    //tag::init3[]
    fun exists2(p: (A) -> Boolean): Boolean =
        foldRight({ false }, { a, b -> p(a) || b() })
    //end::init3[]

    //tag::init4[]
    fun find(p: (A) -> Boolean): Option<A> =
        filter(p).headOption()
    //end::init4[]
}

data class Cons<out A>(
    val head: () -> A,
    val tail: () -> Stream<A>
) : Stream<A>()

object Empty : Stream<Nothing>()

val trace = {
    //tag::init5[]
    Stream.of(1, 2, 3, 4).map { it + 10 }
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList()

    Stream.cons({ 11 }, { Stream.of(2, 3, 4).map { it + 10 } })
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList() // map을 첫 번째 원소에 적용

    Stream.of(2, 3, 4).map { it + 10 }
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList() // filter를 첫 번째 원소에 적용함. 술어가 false를 반환함

    Stream.cons({ 12 }, { Stream.of(3, 4).map { it + 10 } })
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList() // map을 두 번째 원소에 적용

    ConsL(36, Stream.of(3, 4).map { it + 10 }
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList()) // filter를 두 번째 원소에 적용하면, 술어가 참을 반환하고, 두 번째 map을 적용하고, 결과의 첫 번째 원소를 생성함

    ConsL(36, Stream.cons({ 13 }, { Stream.of(4).map { it + 10 } })
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList() // map을 세 번째 원소에 적용
    )

    ConsL(36, Stream.of(4).map { it + 10 }
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList()) // filter를 세 번째 원소에 적용하고, 술어가 거짓을 반환

    ConsL(36, Stream.cons({ 14 }, { Stream.empty<Int>().map { it + 10 } })
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList() // map을 마지막 원소에 적용
    )

    ConsL(36, ConsL(42, Stream.empty<Int>().map { it + 10 }
        .filter { it % 2 == 0 }
        .map { it * 3 }.toList())) // filter를 마지막 원소에 적용하면, 술어가 참을 반환하고, 두 번째 map을 적용하고, 결과의 두 번째 원소를 생성함

    ConsL(36, ConsL(42, NilL)) // 스트림 끝을 표현하는 Empty에 도달함. 이제 map과 filter가 더 할 일이 없음. 빈 스트림은 Nil로 바뀜
    //end::init5[]
}
