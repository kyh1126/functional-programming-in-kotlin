package chapter6.sec2

interface RNG {
    fun nextInt(): Pair<Int, RNG>
}

//tag::init1[]
data class SimpleRNG(val seed: Long) : RNG {
    override fun nextInt(): Pair<Int, RNG> {
        val newSeed =
            (seed * 0x5DEECE66DL + 0xBL) and
                    0xFFFFFFFFFFFFL // 현재 씨앗값을 사용해 새로운 씨앗값을 생성함. and는 비트 AND임
        val nextRNG = SimpleRNG(newSeed) // 다음 상태는 새로운 씨앗값으로부터 생성한 RNG 인스턴스임
        val n = (newSeed ushr 16).toInt() // n 값은 새 의사 난수 정수임. ushr은 msb에 0을 채워 넣는 오른쪽 이진 시프트임
        return n to nextRNG // 반환값은 의사 난수 정수와 다음 RNG 상태가 들어 있는 Pair<Int, RNG>
    }
}
//end::init1[]
