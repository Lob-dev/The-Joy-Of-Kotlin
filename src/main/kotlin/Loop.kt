
fun main(args: Array<String>) {

    // until 는 증가할 범위를 만들어낸다. (끝의 값을 포함하지 않는다.)  0 ~ until
    // step 은 증가하는 값을 만들어낸다.
    // 0, 2, 4, 6, 8
    for (i in 0 until 10 step 2) println(i)

    // 위와 아래의 for 은 동일하다.
    for (i in 0.until(10).step(2)) println(i)

    println("----------")

    // .. 도 증가할 범위를 만들어내며, 끝의 값을 포함한다.
    // 0, 2, 4, 6, 8, 10
    val range = 0 .. 10 step 2
    for (i in range) println(i)

    println("----------")

    val users = arrayListOf("lob", "jay", "mont")
    for (name in users) println(name)

}