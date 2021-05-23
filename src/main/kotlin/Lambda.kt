
fun main(args: Array<String>) {

    // 코틀린에서는 람다를 값처럼 다룰 수 있다.
    // 1. 람다는 메소드의 파라미터로 사용할 수 있다.
    // 2. return 값으로 사용할 수 있다.

    val square = {number: Int -> number+number}
    println(square(10))


    val nameAge = {name: String, age: Int -> "my name is $name I'm $age"}
    println(nameAge("Lob", 24))

    // 확장 함수 Extension Functions
    // 기존 클래스에 변경을 가하지 않고, 새로운 기능을 추가할 수 있다.
    // 익명 함수나 기명 함수로 선언하여 사용할 수 있다.

    // String.(함수 인자) -> 함수 반환 타입 = {함수 본문}
    // 밑의 선언의 경우 인자 없는 함수를 추가.
    val stringExtend: String.() -> String = {this + " hello!"}
    println(stringExtend("string"))


    val lob = "Lob"
    println(lob.stringExtend())

    fun extendString(name: String) : String {
        return name.stringExtend()
    }
    println(extendString("Lob"))

    val name = "Lob"
    val job  = "student"
    val age  = 24
    val stringExtend2: String.(String, Int) -> String = { job: String, age: Int -> "$this is $job and age $age hello!"}
    println(name.stringExtend2(job, age))


    fun <T> ArrayList<T>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }
    val users: ArrayList<String> = arrayListOf("Lob", "Babo", "Bol")
    users.swap(0, 2)
    println(users)

    val numbers: ArrayList<Int> = arrayListOf(10, 20, 30)
    numbers.swap(0, 2)
    println(numbers)

}