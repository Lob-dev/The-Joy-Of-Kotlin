package lob.study.kotlin.example.function

import lob.study.kotlin.account.domain.service.AccountService
import lob.study.kotlin.article.domain.service.ArticleService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import java.time.Duration
import java.util.*
import kotlin.collections.ArrayList

// 스프링캠프 2018 [TrackB Session2] : 쿠팡 Kotlin Backend 적용기
fun hello(array:ArrayList<String>, offset:Int = 0, length:Int = array.size) {
    TODO()
}


// extension function
fun Int.millis(): Duration = Duration.ofMillis(this.toLong())
fun Int.seconds(): Duration = Duration.ofSeconds(this.toLong())
fun Int.isEven(): Boolean = ((this % 2) == 0)

fun main(arg:Array<String>) {
    val second: Duration = 10.seconds() + 500.millis();
    println("second : $second ms")
    nullableSize(null)
    nullableSize("str")

    safeCasting(null)
    safeCasting("str")

    val number = 4
    println(number.isEven())
}

// 동일한 로직
// val size: Int = if (text != null) text.length else -1
fun nullableSize(text: String?) {

    // null 확인 필요시마다 ?을 지정
        // text? => text == null (가능하다)
        // variable or value ?: default value (variable 이 null 이면, default value 를 적용)
    val size = text?.length ?: -1
    println("size : $size")
}

fun safeCasting(text: String?) {
    val localString: String? = text

    // 안전 자료형 변환
    val value = text as? Int ?: 0
    println("casting : $value")
}

fun guardNullWithElvis(text: String?) {
    text ?: throw RuntimeException()

    val length = text.length
    println(length)
}

fun kotlinDsl(list: ArrayList<String>) {
    // 기존 Lambda
    list.forEach { str ->
        println(str)
    }

    // kotlin support dsl
        // it 이 전달되는 Param
    list.forEach {
        println(it)
    }
}

fun smartCast(text: String?) {
    text ?: return

    // not nullable
    println(text.lowercase(Locale.getDefault()))
}

fun branch(key: String) = when(key) {
    "A" -> println("A")
    "B" -> println("B")
    "C" -> println("C")
    else -> println("Init")
}

fun textFormatted(text: String, name: String) = text
    .trim()
    .capitalize()
    .replace("{name}", name)

fun formatClient() {
    val formatted = textFormatted("hello, {name}", "Marcin")
    println(formatted) // Hello, Marcin
}

@Controller
class SampleController {

    /*
    @Autowired
    private var articleService: ArticleService -> Property must be initialized or be abstract
    */

    // Field DI One
    @Autowired
    private var articleService: ArticleService? = null

    // Field DI Two
    @Autowired
    private lateinit var accountService: AccountService

    // Constructor DI 는 동일

    // Required parameter 여부는 type 의 ? 을 이용해 지정 가능하다.
}