import java.time.Instant

fun main(args: Array<String>) {

    // 자바에서 정적 맴버를 호출하는 것처럼 companion object 의 메서드를 호출할 수 있다.
    User.create("string")

    // Companion 을 명시하고 작성할 수 있다.
    User.Companion.create("string")

}

// 코틀린은 정적 맴버를 companion object 라는 요소를 통해 기능을 제공한다.
data class User(val name: String, val date: Instant = Instant.now()) {
    companion object {
        fun create(xml: String): User {
            TODO(""" 
                Write an Implementation creating 
                a Person from an xml string
                """)
        }
    }
}