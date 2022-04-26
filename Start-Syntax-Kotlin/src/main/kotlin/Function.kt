
fun main(args: Array<String>) {

    // function structure
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    // 함수 본문이 한 줄인 경우 { } scope 를 축약할 수 있다.
    fun add2(a: Int, b: Int): Int = a + b

    // 이런 expression syntax 형식에서 추론 가능한 타입은 반환 타입을 생략할 수 있다.
    fun add3(a: Int, b: Int) = a + b

    println(add(1,2))
    println(add2(1,2))
    println(add3(1,2))

    // local function
    fun sumOfPrimes(limit: Int): Long {
        val seq: Sequence<Long> = sequenceOf(2L) + generateSequence(3L) {
            it + 2
        }.takeWhile {
            it < limit
        }

        // 해당 함수는 sumOfPrimes 외부에서 호출할 수 없다.
        // isPrime 은 seq 값을 close over 하기 때문이다. 이러한 구조를 closure 라고 한다.
        fun isPrime(n: Long): Boolean = seq.takeWhile {
            it * it <= n
        }.all {
            n % it != 0L
        }

        return seq.filter(::isPrime).sum()
    }

    class Sample {
        // 코틀린에서는 함수를 override 할 때 무조건 override 키워드를 명시하여야 한다.
        override fun hashCode(): Int {
            return javaClass.hashCode()
        }
    }

}