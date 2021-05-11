import java.lang.StringBuilder

fun main(args: Array<String>) {

    // 리터럴 정의 방식
    val str1 = "str"
    val str2 = "str"

    // 비 리터럴 정의 방식 = new 처럼 객체를 생성함
    val str3 = String(StringBuilder("str"))
    val str4 = String(StringBuilder("str"))

    val num1 = 1
    val num2 = 1


    // Structural Equality (‘==’)
        // Basic Type 을 비교할 때에는 값을 비교하게 된다.
    println(num1 == num2)

        // Reference Type 을 비교할 때에는 내부적으로 equals 를 호출하여 값을 비교하게 된다.
    println(str1 == str2)
    println(str3 == str4)


    // Referential equality (‘===’)
        // Reference Type 을 참조 값으로 비교하려면 === 을 사용하면 된다. 이는 자바의 == 와 동일한 역할을 한다.
    println(str3 === str4)

}
