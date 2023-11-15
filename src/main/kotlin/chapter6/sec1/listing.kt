package chapter6.sec1

//tag::init1[]
val rng = kotlin.random.Random // 현재 시스템 시간에 의해 시드가 정해진 새 난수 생성기를 만듦
//println(rng.nextDouble())
//println(rng.nextDouble())
//rng.nextInt(6) // 0부터 5 사이의 난수를 얻음

fun rollDie(): Int { // 1 이상 6 이하인 난수를 반환해야만 함
    val rng = kotlin.random.Random
    return rng.nextInt(6) // 0부터 5 사이의 난수를 반환
}
//end::init1[]

//tag::init2[]
fun rollDie2(rng: kotlin.random.Random): Int = rng.nextInt(6)
//end::init2[]
