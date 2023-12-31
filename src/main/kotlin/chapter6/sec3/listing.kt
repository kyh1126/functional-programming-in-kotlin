package chapter6.sec3

class Repository

//tag::init1[]
class MutatingSequencer {
    private var repo: Repository = TODO()
    fun nextInt(): Int = TODO()
    fun nextDouble(): Double = TODO()
}
//end::init1[]

//tag::init2[]
interface StateActionSequencer {
    fun nextInt(): Pair<Int, StateActionSequencer>
    fun nextDouble(): Pair<Double, StateActionSequencer>
}
//end::init2[]

interface RNG {
    fun nextInt(): Pair<Int, RNG>
}

//tag::init3[]
fun randomPair(rng: RNG): Pair<Int, Int> {
    val (i1, _) = rng.nextInt()
    val (i2, _) = rng.nextInt()
    return i1 to i2
}
//end::init3[]

//tag::init4[]
fun randomPair2(rng: RNG): Pair<Pair<Int, Int>, RNG> {
    val (i1, rng2) = rng.nextInt()
    val (i2, rng3) = rng2.nextInt() // 여기서 rng 대신 rng2를 씀
    return (i1 to i2) to rng3 // 난수를 생성한 후 마지막 상태로 rng3를 반환해서 호출자가 계속 난수를 생성할 수 있게 함
}
//end::init4[]
