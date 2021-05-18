
fun main(args: Array<String>) {

    println(Singleton.toString())

    println(Insatance.toString())

    val value1 = Insatance
    val value2 = Insatance

    println(value1 == value2)
    println(value1 === value2)

}

// 싱글턴을 만들 때에는 class 를 object 로 변경하면 된다.
object Singleton {
    override fun toString(): String {
        return "lob"
    }
}

open class Hello(private val name: String) {
    override fun toString(): String {
        return name
    }
}

object Insatance: Hello("lob")