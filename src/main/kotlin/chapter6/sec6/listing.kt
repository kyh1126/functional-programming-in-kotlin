package chapter6.sec6

import arrow.core.Id
import arrow.core.Tuple2
import arrow.core.extensions.id.monad.monad
import arrow.mtl.State
import arrow.mtl.extensions.fx

interface RNG {
    fun nextInt(): Pair<Int, RNG>
}

//tag::init1[]
val int: State<RNG, Int> = TODO() // 난수 정수를 하나 생성하는 State<RNG, Int>

fun ints(x: Int): State<RNG, List<Int>> = TODO() // x개의 난수 정수를 생성하는 State<RNG, List<Int>>를 반환

fun <A, B> flatMap(
    s: State<RNG, A>,
    f: (A) -> State<RNG, B>
): State<RNG, B> = TODO() // State<RNG, A>에 대해 A를 State<RNG, B>로 변환하는 함수를 적용해주는 flatMap 함수

fun <A, B> map(
    s: State<RNG, A>,
    f: (A) -> B
): State<RNG, B> = TODO() // State<RNG, A>에 대해 A를 B로 변환하는 함수를 적용해주는 map 함수
//end::init1[]

//tag::init2[]
val ns: State<RNG, List<Int>> =
    flatMap(int) { x -> // int는 정수 하나를 생성함
        flatMap(int) { y -> // int는 정수 하나를 생성함
            map(ints(x)) { xs -> // ints(x)는 길이가 x인 난수 정수 리스트를 생성함
                xs.map { it % y } // 리스트의 모든 원소를 y로 나눈 나머지로 대치함
            }
        }
    }
//end::init2[]

//tag::init3[]
val ns2: State<RNG, List<Int>> =
    State.fx(Id.monad()) { // 코드 블록을 State.fx(Id.monad())에 전달함으로써 for 컴프리헨션을 시작함
        val x: Int = int.bind() // x라는 이름의 Int에 int를 바인드
        val y: Int = int.bind() // y라는 이름의 Int에 int를 바인드
        val xs: List<Int> = ints(x).bind() // 길이가 x인 List<Int>에 ints(x)를 바인드
        xs.map { it % y } // xs의 모든 원소를 y로 나눈 나머지로 대치하고 결과를 반환함
    }
//end::init3[]

//tag::init4[]
fun <S> modify(f: (S) -> S): State<S, Unit> =
    State.fx(Id.monad()) { // State에 대한 for 컴프리헨션을 설정함
        val s: S = get<S>().bind() // 현재 상태를 얻어서 s에 대입
        set(f(s)).bind() // s에 f를 적용한 새 상태를 설정
    }
//end::init4[]

//tag::init5[]
fun <S> get(): State<S, S> =
    State { s -> Tuple2(s, s) }
//end::init5[]

//tag::init6[]
fun <S> set(s: S): State<S, Unit> =
    State { Tuple2(s, Unit) }
//end::init6[]
