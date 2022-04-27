package com.lob.kotlin.demo.syntax

fun main(args: Array<String>) {

    // function structure
    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun add2(a: Int, b: Int): () -> Int = { a + b }
    fun add3(a: Int, b: Int): Function<Int> = { a + b }

    // 함수 본문이 한 줄인 경우 { } scope 를 축약할 수 있다.
    // Function<> 은 { } scope 가 명시적으로 정의되었을 때 사용할 수 있는 인터페이스이다.
    fun add5(a: Int, b: Int): Int = a + b

    // 이런 expression-body (syntax) 형식에서 추론 가능하다면 반환 타입을 생략할 수 있다.
    fun add6(a: Int, b: Int) = a + b

    println(add(1, 2))
    println(add2(2, 2))
    println(add3(3, 2))
    println(add5(4, 2))
    println(add6(5, 2))

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

    val squares = IntArray(5) { number -> number * number }


    // function default parameter
    fun readInt(radix: Int) = readLine()!!.toInt(radix)
    fun binaryInt() = readInt(2)
    fun octalInt() = readInt(8)
    fun decimalInt() = readInt(10)
    fun hexInt() = readInt(16)
    fun readIntPair(): IntArray = intArrayOf(decimalInt(), decimalInt())


    // HOR
    fun <T> readlineBy(lambda: (String?) -> T) = lambda(readLine())
    fun decimalInt2() = readlineBy { it?.toInt(10) }

    fun aggregate(numbers: IntArray, operation: (Int, Int) -> Int): Int {
        var result = numbers.firstOrNull() ?: throw IllegalArgumentException("Empty Array")

        (1..numbers.lastIndex).forEach { index ->
            result = operation(result, numbers[index])
        }
        return result
    }

    fun sum(numbers: IntArray) = aggregate(numbers) { number1, number2 -> number1 + number2 }
    fun max(numbers: IntArray) = aggregate(numbers) { number1, number2 -> if (number2 > number1) number2 else number1 }
    println("sum = ${sum(intArrayOf(1, 2, 3))}")
    println("max = ${max(intArrayOf(1, 2, 3))}")


    // callable reference (function)
    fun operation(number1: Int, number2: Int) = number1 + number2
    aggregate(numbers = intArrayOf(1, 2, 3), ::operation)

    class Greeting(val message: String)
    val sayGreeting = ::Greeting
    val greeting: Greeting = sayGreeting("Hello Kotlin!")


    // tail recursive function
    tailrec fun binaryIndexOf(
        targetValue: Int,
        array: IntArray,
        from: Int = 0,
        to: Int = array.size
    ): Int {
        if (from == to) return -1
        val middleIndex = (from + to - 1) / 2
        val middleValue = array[middleIndex]
        return when {
            middleValue < targetValue -> binaryIndexOf(targetValue, array, middleIndex + 1, to)
            middleValue > targetValue -> binaryIndexOf(targetValue, array, from, middleIndex)
            else -> middleIndex
        }
    }

    // 확장 함수 Extension Functions
    // 기존 클래스에 변경을 가하지 않고, 새로운 기능을 추가할 수 있다.
    // 익명 함수나 기명 함수로 선언하여 사용할 수 있다.

    // String.(함수 인자) -> 함수 반환 타입 = {함수 본문}
    // 밑의 선언의 경우 인자 없는 함수를 추가.
    val stringExtend: String.() -> String = { "$this hello!" }
    println(stringExtend("string"))


    val lob = "Lob"
    println(lob.stringExtend())

    fun extendString(name: String): String {
        return name.stringExtend()
    }
    println(extendString("Lob"))


// scope function (run, with, let, apply, also)
}


// inline function
/*
    Kotlin에서 사용되는 고차 함수와 함수 값은 객체로 표현되기 때문에 성능 차원에서 오버헤드가 발생한다.
    또한 이러한 함수가 외부 영역의 변수를 참조하고 있다면 이러한 변수 또한 Capturing 하는 구조로 만들어서 전달하여야 한다.
    이러한 함수는 기본적으로 가상 호출을 통해 어떠한 함수 구현을 사용할지 디스패치 해야하기 때문에 다양한 부분에서 성능 하락이 있을 수 있다.
    이러한 Runtime over-head를 제거하는 방법으로 Kotlin은 Inline 문법을 제공하는데 이는 컴파일 시점에서 호출하는 함수 영역에 사용하는 함수 본문을
    포함시키는 방식이다. (이는 JVM의 Code Optimazation 기법 중 하나인 method inlining 기법과 유사한 방식의 최적화이다.

    반대로 noinline 변경자를 통해 inline func에서 사용하는 lambda 인자를 inlining 대상에서 제외할 수 있다. 변수가 nullable 하다면
    inline 기법을 사용할 수 없으며, class의 private, internal 변수를 사용하는 경우 최종적으로 캡슐화를 깨기 때문에 권장하지는 않는다.
 */
inline fun hello(action: () -> Unit) = run { println("Hello") }