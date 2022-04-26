package lob.study.kotlin.example.function

import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.kotlin.toObservable
import io.reactivex.rxjava3.subjects.PublishSubject
import io.reactivex.rxjava3.subjects.Subject
import java.util.*


fun main(arg:Array<String>) {
    observable()
    reactiveEvenOdd()
}

private fun observable() {
    val list: List<Any> = listOf("One", 2, "Three", "Four", 4.5, "Five", 6.0f)
    val observable: Observable<Any> = list.toObservable()
        .apply {
            subscribeBy(
                onNext = { println(it) },
                onComplete = { println("Done!") },
                onError = { it.printStackTrace() }
            )
        }
}

fun reactiveEvenOdd() {
    val subject: Subject<Int> = PublishSubject.create()
    subject.map {
        it.isEven() // int to bool
    }.subscribeBy (
        onNext = { println("The number is ${if (it) "Even" else "Odd"}") },
        onComplete = { println("Done!") },
        onError = { println("Error! $it") }
    )
    subject.onNext(10)
    subject.onNext(9)
    subject.onNext(2)
    subject.onNext(5)
    subject.onNext(7)
    subject.onError(RuntimeException())
}

fun reactiveCalculatorClient() {
    val calculator = ReactiveCalculator(15, 10)

    var line:String
    do {
        line = readLine() ?: "exit"
        calculator.handleInput(line)
    } while ("exit" !in line.lowercase(Locale.getDefault()))
}

class ReactiveCalculator(num1: Int, num2: Int) {
    internal val subjectAdd: Subject<Pair<Int, Int>> = PublishSubject.create()
    internal val subjectSub: Subject<Pair<Int, Int>> = PublishSubject.create()
    internal val subjectMulti: Subject<Pair<Int, Int>> = PublishSubject.create()
    internal val subjectDiv: Subject<Pair<Int, Int>> = PublishSubject.create()

    internal val subjectCalc: Subject<ReactiveCalculator> = PublishSubject.create()
    internal var nums:Pair<Int,Int> = Pair(0,0)

    init{
        nums = Pair(num1, num2)

        subjectAdd.map {
            it.first + it.second
        }.subscribe {
            println("Add = $it")
        }
        subjectSub.map {
            it.first - it.second
        }.subscribe {
            println("Subtract = $it")
        }
        subjectMulti.map {
            it.first * it.second
        }.subscribe {
            println("Multiply = $it")
        }
        subjectDiv.map {
            it.first / (it.second * 1.0)
        }.subscribe {
            println("Divide = $it")
        }

        subjectCalc.subscribe {
            with(it) {
                addition()
                subtraction()
                multiplication()
                division()
            }
        }
        subjectCalc.onNext(this)
    }


    private fun addition() = subjectAdd.onNext(nums)
    private fun subtraction() = subjectSub.onNext(nums)
    private fun multiplication() = subjectMulti.onNext(nums)
    private fun division() = subjectDiv.onNext(nums)

    private fun modifyNumbers (a:Int = nums.first, b: Int = nums.second) {
        nums = Pair(a,b)
        subjectCalc.onNext(this)
    }

    fun handleInput(inputLine:String?) {
        if(!inputLine.equals("exit")) {
            val pattern: java.util.regex.Pattern = java.util.regex.Pattern.compile("([a|b])(?:\\s)?=(?:\\s)?(\\d*)");

            val matcher: java.util.regex.Matcher = pattern.matcher(inputLine)

            var a = 0
            var b = 0
            if (matcher.matches() && (matcher.group(1) != null) && matcher.group(2) != null) {
                when {
                    matcher.group(1).lowercase(Locale.getDefault()) == "a" -> {
                        a = matcher.group(2).toInt()
                    }
                    matcher.group(1).lowercase(Locale.getDefault()) == "b" -> {
                        b = matcher.group(2).toInt()
                    }
                }
            }
            modifyNumbers(a = a, b = b)
        }
    }
}