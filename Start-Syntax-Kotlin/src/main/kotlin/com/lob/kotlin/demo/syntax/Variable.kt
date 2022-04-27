package com.lob.kotlin.demo.syntax

fun main(args: Array<String>) {

    // 불변 참조 변수 (final Variable) + 타입 명시
    // 하지만 재할당을 금지하는 것이지 진정한 불변은 아니다. -> 객체를 참조하고 내부 속성이 변경된다면 이는 불변하지 않음을 의미한다.
    // 이를 위해 해당 객체도 불변을 지키는 방식으로 구현되어야 한다.
    val name: String = "lob"

    // 타입 추론 (type inference)
    val name2 = "lob"

    // 코틀린은 기본적으로 초기화 되지 않은 참조 변수를 쓸 수 없도록 막는다. 그러니 지금 사용되지 않는 것은 무조건 null 로 초기화되어야 한다.
    // 참조가 가리키는 대상이 바뀌지 않으면 프로그램을 추론하는 것이 쉽기 때문에, val 을 사용하는 것이 권장된다.
    var name3 = "lob"
    name3 = "stupid lob"

    // 개인적으로 val + 타입 명시 방식으로 코딩하는 방식이 안정적인 것 같다.


    // 코틀린의 지연 초기화

    // Late initialization : 필요할 때 초기화하고 사용할 수 있는 방식이다. -> 초기화되지 않은 상태를 접근하면 NPE가 발생한다.

    // lateinit 을 통한 Late initialization 방식
    // var 변수와 타입을 미리 선언한다. -> 해당 방식은 var 타입만을 사용할 수 있으며, Double, Int, Boolean 같은 Non-object Type,
    // Null, Custom getter/setter 는 사용할 수 없다.

    // 즉 String 과 Object 만 사용할 수 있다.
    lateinit var area: String

    // 필요할 때 사용한다.
    area = "seoul"

    // 자바 코드로 확인하면 우선 @NotNull 이 부착된 초기화 되지 않은 상태 변수가 선언되고 해당 변수의 메서드를 접근할 때 Null 체크가 포함되어 있다.


    // Lazy initialization : 변수를 선언할 때 초기화 코드도 함깨 정의한 뒤 사용될 때 초기화 코드가 동작한다.

    // lazy { .. } 부분은 변수가 호출되었을 때 한번 호출되는 Scope 이다.
    // 할당할 값은 기본적으로 Scope 맨 밑에 존재하여야 한다.
    // val 변수만 사용가능하며, primitive 타입을 사용할 수 있고, Nullable, Non-null 상관없이 사용 가능하다.

    val zone: Int by lazy {
        println("lazy")
        10
    }

    // lazy 는 메서드 참조를 사용할 수 있다.
    val location: String by lazy(::getLocation)

}

fun getLocation(): String = "location"
