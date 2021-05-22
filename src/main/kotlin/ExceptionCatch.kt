
fun main(args: Array<String>) {

    // try - catch 구문도 식으로 평가된다
    val num : Int = try {
        args[0].toInt()
    } catch(e: Exception) {
        0
    } finally {
        println("꽑")
    }

}