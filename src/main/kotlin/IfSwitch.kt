
fun main(args: Array<String>) {

    fun sample(a : Int, b : Int) : Int {
        if (a > b) {
            return a
        } else {
            return b
        }
    }

    // 코틀린에서 if 구문은 값으로 평가할 수 있는 식으로 인식된다.
    fun sample2(a : Int, b : Int) : Int {
        return if (a > b) {
            a
        } else {
            b
        }
    }

    // 자바와 같이 { } scope 안에 한 줄만 존재한다면 생략이 가능하다.
    fun sample3(a : Int, b : Int) {

        val value = if (a > b)
                        a
                    else
                        b

    }

    fun sample4(value : Int) {
        when(value) {
            0 -> println("0")
            1 -> println("1")
            2,3 -> println("2")
        }

        when(value) {
            in 1..3 -> println("꽑")
            else -> println("왉")
        }

        // when 도 if 문과 같이 식으로 인식된다.
        val foo = when(value) {
            0 -> 0
            1 -> 1
            else -> 2
        }

        println("$foo")
    }

}