const val PURCH_FOR_MELOMAN = 3u
const val FIRST_LEVEL_DISCOUNT = 100_00u
const val SECOND_LEVEL_DISCOUNT = 5u


fun main() {
    var lostMoney = 0u
    var isMeloman = false
    var purchCount = 0u

    while (true) {
        print("Введите сумму, с которой вы готовы расстаться (для выхода введите '0'): ")
        val inputMoney = readLine()?.toUInt()?.times(100u)
        if (inputMoney == 0u) break
        val discount = when (lostMoney) {
            in 0U..1_000_00u -> 0u
            in 1_001_00u..10_00_000u -> FIRST_LEVEL_DISCOUNT
            else -> (inputMoney!! / 100u) * SECOND_LEVEL_DISCOUNT
        }
        if (purchCount >= PURCH_FOR_MELOMAN) isMeloman = true
        if (inputMoney!! <= FIRST_LEVEL_DISCOUNT && discount == FIRST_LEVEL_DISCOUNT) {
            println("Введите сумму больше 100 руб")
            continue
        }
        val finalDiscount = if (isMeloman) (inputMoney.minus(discount)).div(100u).plus(discount) else discount
        lostMoney += if (discount > 0u) inputMoney.minus(finalDiscount) else inputMoney
        if (inputMoney != 0u) purchCount++
        val penny = (inputMoney.minus(finalDiscount)).rem(100u)
        println("Скидка ${finalDiscount.div(100u)} rub ${finalDiscount.rem(100u)}\n" +
                "Итого ${inputMoney.minus(finalDiscount).div(100u)} rub $penny kop\n")
    }
}