package chapter1.sec1

val listing1 = {
    class CreditCard {
        fun charge(price: Float): Unit = TODO()
    }

    data class Coffee(val price: Float = 2.50F)

    //tag::init1[]
    class Cafe {
        fun buyCoffee(cc: CreditCard): Coffee {
            val cup = Coffee() // 새로운 커피를 한 잔 초기화한다
            cc.charge(cup.price) // 커피 가격을 신용카드로 청구한다. 부수 효과!
            return cup // 커피를 반환한다
        }
    }
    //end::init1[]
}

val listing2 = {
    data class Coffee(val price: Float = 2.95F)

    class CreditCard

    class Payments {
        fun charge(cc: CreditCard, price: Float): Unit = TODO()
    }

    //tag::init2[]
    class Cafe {
        fun buyCoffee(cc: CreditCard, p: Payments): Coffee {
            val cup = Coffee()
            p.charge(cc, cup.price) // 부수 효과!
            return cup
        }
    }
    //end::init2[]
}

val listing3 = {
    class CreditCard

    data class Coffee(val price: Float = 2.50F)

    data class Charge(val cc: CreditCard, val amount: Float)

    //tag::init3[]
    class Cafe {
        fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> {
            val cup = Coffee()
            return Pair(cup, Charge(cc, cup.price))
        }
    }
    //end::init3[]
}

val listing4 = {
    class CreditCard

    //tag::init4[]
    data class Charge(val cc: CreditCard, val amount: Float) { // 생성자와 불변 필드가 있는 데이터 클래스 선언
        fun combine(other: Charge): Charge = // 같은 신용카드에 대한 청구를 하나로 묶음
            if (cc == other.cc) // 같은 카드인지 검사함. 같은 카드가 아닌 경우 예외를 발생시킴
                Charge(cc, amount + other.amount) // 이 Charge와 다른 Charge의 금액을 합산한 새로운 Charge를 반환
            else throw Exception(
                "Cannot combine charges to different cards"
            )
    }
    //end::init4[]
}

val listing5 = {
    class CreditCard

    data class Coffee(val price: Float = 2.50F)

    data class Charge(val cc: CreditCard, val amount: Float) {
        fun combine(other: Charge): Charge = TODO()
    }

    //tag::init5[]
    class Cafe {
        fun buyCoffee(cc: CreditCard): Pair<Coffee, Charge> = TODO()

        fun buyCoffees(
            cc: CreditCard,
            n: Int
        ): Pair<List<Coffee>, Charge> {

            val purchases: List<Pair<Coffee, Charge>> =
                List(n) { buyCoffee(cc) } // 자체적으로 초기화되는 리스트를 생성한다

            val (coffees, charges) = purchases.unzip() // Pair의 리스트를 두 리스트로 분리한다

            return Pair(
                coffees,
                charges.reduce { c1, c2 -> c1.combine(c2) }
            ) // coffees를 한 Charge로 합친 출력을 생성한다
        }
    }
    //end::init5[]
}

val listing6 = {
    class CreditCard

    data class Charge(val cc: CreditCard, val amount: Float) {
        fun combine(other: Charge): Charge = TODO()
    }

    //tag::init6[]
    fun List<Charge>.coalesce(): List<Charge> =
        this.groupBy { it.cc }.values
            .map { it.reduce { a, b -> a.combine(b) } }
    //end::init6[]
}
