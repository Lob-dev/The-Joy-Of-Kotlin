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

    val person1 = Person("lob")
    val person2 = Person("lob")

    val human1 = Human("lob")
    val human2 = Human("lob")

    // Structural Equality (‘==’)
        // Basic Type 을 비교할 때에는 값을 비교하게 된다.
    print("num == : ")
    println(num1 == num2)

        // Reference Type 을 비교할 때에는 내부적으로 equals 를 호출하여 값을 비교하게 된다.
    print("str == : ")
    println(str1 == str2)

    print("str == : ")
    println(str3 == str4)

    // data class 가 아니라 일반 class 라면 equals 가 재정의되지 않고 object 의 것으로 비교되기에 false 가 나온다.
    print("person == : ")
    println(person1 == person2)

    print("human == : ")
    println(human1 == human2)

    // Referential equality (‘===’)
        // Reference Type 을 참조 값으로 비교하려면 === 을 사용하면 된다. 이는 자바의 == 와 동일한 역할을 한다.
    print("str === : ")
    println(str3 === str4)

    print("person === : ")
    println(person1 === person2)

    print("human === : ")
    println(human1 === human2)

}

// data class 는 equals, hashcode toString 를 값을 기준으로 재정의하며 객체의 속성 값을 복사하여 새로운 객체를 생성하는 copy 를 생성한다.
data class Person(val name: String)

class Human(val name: String)